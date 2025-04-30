package com.healthturing.healthturing_server.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.exceptions.EmailEmitErrorException;
import com.healthturing.healthturing_server.exceptions.EmailNotConfirmedException;
import com.healthturing.healthturing_server.exceptions.InvalidJwtException;
import com.healthturing.healthturing_server.exceptions.InvalidTokenException;
import com.healthturing.healthturing_server.exceptions.UserAlreadyExistsException;
import com.healthturing.healthturing_server.exceptions.UserNotFoundException;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.VerificationToken;
import com.healthturing.healthturing_server.repositories.UserRepository;
import com.healthturing.healthturing_server.repositories.VerificationTokenRepository;
import com.healthturing.healthturing_server.validations.ValidationsFunctions;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Value("${url.client}")
    private String clientUrl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final ValidationsFunctions validationsFunctions;

    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, ValidationsFunctions validationsFunctions) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.validationsFunctions = validationsFunctions;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public void register(String email, String name, String password) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new RuntimeException("Los campos no pueden estar vacíos");
        }

        if (!validationsFunctions.isValidEmail(email)) {
            throw new RuntimeException("El formato de email es incorrecto");
        }

        if (!validationsFunctions.isValidPassword(password)) {
            throw new RuntimeException("La contraseña no cumple con los requisitos de seguridad");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("El usuario ya existe");
        }


        User user = new User(email, name, passwordEncoder.encode(password));
        Long expiration = System.currentTimeMillis() + 1000 * 3600 * 24 * 3;
        String token = UUID.randomUUID().toString().replace("-", "");

        VerificationToken verificationToken = new VerificationToken(user, token,
        expiration);
        
        userRepository.save(user);
        verificationTokenRepository.save(verificationToken);



        registerUser(email, token);
    }

    public String login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("No existe nigún usuario registrado con este email");
        }

        User user = userOptional.get();

        if (!user.isEnabled()) {
            throw new EmailNotConfirmedException("Debes confirmar tu correo antes de iniciar sesión.");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Las credenciales introducidas son incorrectas");
        }

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
            throw new EmailEmitErrorException("Error al enviar el email de confirmación");
        }
    }

    @Transactional
    public String confirmEmail(String token) {

        VerificationToken verificationToken =  verificationTokenRepository.findByToken(token)
        .orElseThrow(() -> new InvalidTokenException("Token no válido"));

        if(verificationToken.getExpireDate()<System.currentTimeMillis()){
            throw new InvalidTokenException("Token no válido");
        }

        User user = userRepository.findByVerificationToken(verificationToken)
        .orElseThrow(() -> new InvalidJwtException("El token no es válido"));


        user.setEnabled(true);
        user.setVerificationToken(null);

        userRepository.save(user);

        verificationTokenRepository.delete(verificationToken);
        verificationTokenRepository.flush();

        return "Email confirmado con éxito";
    }

}
