package com.healthturing.healthturing_server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.exceptions.EmailEmitErrorException;
import com.healthturing.healthturing_server.exceptions.EmailNotConfirmedException;
import com.healthturing.healthturing_server.exceptions.UserAlreadyExistsException;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.VerificationToken;
import com.healthturing.healthturing_server.repositories.UserRepository;
import com.healthturing.healthturing_server.repositories.VerificationTokenRepository;

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

    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public void register(String email, String name, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("El usuario ya existe");
        }

        // TODO: Validaciones de campos

        User user = new User(email, name, passwordEncoder.encode(password));
        Long expiration = System.currentTimeMillis() + 1000 * 3600 * 24 * 3;
        String token = jwtService.UserAppToken(user, expiration);

        // TODO ver si es necesario cambiar la fecha de expiracion a formato date para
        // almacenarla mejor
        // VerificationToken verificationToken = new VerificationToken(user, token,
        // expiration);
        // verificationTokenRepository.save(verificationToken);

        userRepository.save(user);

        // registerUser(email, verificationToken.getToken());
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

    public String confirmEmail(String token) {

        // TODO: hacer errores de orElseThrow de verToken y user
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElseThrow();

        User user = userRepository.findByVerificationToken(verificationToken).orElseThrow();

        user.setEnabled(true);

        userRepository.save(user);

        verificationTokenRepository.delete(verificationToken);

        return "Email confirmado con éxito";
    }

}
