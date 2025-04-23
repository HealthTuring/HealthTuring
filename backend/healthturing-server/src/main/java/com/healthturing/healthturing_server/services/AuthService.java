package com.healthturing.healthturing_server.services;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.AssertingParty.Verification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.models.Role;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.VerificationToken;
import com.healthturing.healthturing_server.models.enums.RoleEnum;
import com.healthturing.healthturing_server.repositories.RoleRepository;
import com.healthturing.healthturing_server.repositories.UserRepository;

import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService {

     @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired //no va autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }


    @Transactional
    public void register(String email, String name, String password, RoleEnum role) {

        //TODO: Validaciones de campos

        User user = new User(email, name, passwordEncoder.encode(password), role);
        Long expiration = System.currentTimeMillis()+ 1000 *3600 * 24 * 3;
        String token = jwtService.UserAppToken(user, expiration);

        //TODO ver si es necesario cambiar la fecha de expiracion a formato date para almacenarla mejor
        VerificationToken verificationToken = new VerificationToken(user, token, expiration);
        
        
        registerApplication(email, verificationToken);
    }

    
    public String login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
    
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("Email o contraseña incorrectos.");
        }

        User user = userOptional.get();

        if (!user.isEmailConfirm()) {
            throw new EmailNotConfirmedException("Debes confirmar tu correo antes de iniciar sesión.");
        }
    
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    
        return jwtService.generateToken(user);
    }

     public void registerUser(String email, String token) {

        String confirmationLink = clientUrl + "/auth/email-confirmation/" + token;
        String htmlContent = "<html>"
                           + "<body>"
                           + "<h1>Confirmación de Registro</h1>"
                           + "<p>Gracias por registrarte. Por favor, haz clic en el siguiente enlace para confirmar tu registro:</p>"
                           + "<a href=\"" + confirmationLink + "\">Confirmar Registro</a>"
                           + "</body>"
                           + "</html>";

        try {
            emailSenderService.sendHtmlEmail(email, "Confirmación de Registro", htmlContent);
        } catch (Exception e) {
            throw new ErrorSendEmailException("Error al enviar el email de confirmación");
        }
    }

    public String confirmEmail(String token) {
        Optional<User> userOptional = userRepository.findByConfirmationToken(token);

        if (!userOptional.isPresent()) {
            throw new InvalidTokenException("Token de confirmación inválido");
        }

        User user = userOptional.orElseThrow();
        user.setEmailConfirm(true);
        user.setConfirmationToken(null);
        userRepository.save(user);

        return "Email confirmado con éxito";
    }
   
  



  
    
}
