package com.healthturing.healthturing_server.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.exceptions.EmailNotConfirmedException;
import com.healthturing.healthturing_server.exceptions.InvalidJwtException;
import com.healthturing.healthturing_server.exceptions.InvalidTokenException;
import com.healthturing.healthturing_server.exceptions.UserAlreadyExistsException;
import com.healthturing.healthturing_server.exceptions.UserNotFoundException;
import com.healthturing.healthturing_server.models.DoctorRegistrationRequest;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.VerificationToken;
import com.healthturing.healthturing_server.repositories.DoctorRegistrationRequestRepository;
import com.healthturing.healthturing_server.repositories.UserRepository;
import com.healthturing.healthturing_server.repositories.VerificationTokenRepository;
import com.healthturing.healthturing_server.validations.ValidationsFunctions;

/**
 * Servicio que gestiona las consultas relacionadas con la autenticación
 * Es empleada por AuthController para registrar, verificar, login...
 * Emplea los repositorios de user y verificationToken, los servicios JwtService
 * y EmailSenderService
 * y las clases AtuhenticacionManager, PasswordEncoder (Servicios implementados)
 * y ValidationFunctions(localizado en validations)
 */
@Service
public class AuthService {

    @Value("${url.client}")
    private String clientUrl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRegistrationRequestRepository doctorRegistrationRequestRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private ValidationsFunctions validationsFunctions;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailTemplateService emailTemplateService;

    /**
     * Constructor para las dependencias necesarias que no son services
     * 
     * @param authenticationManager
     * @param passwordEncoder
     * @param validationsFunctions
     */
    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
            EmailTemplateService emailTemplateService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.emailTemplateService = emailTemplateService;
    }

    /**
     * Comprueba que los campos cumplen las condiciones para crear un nuevo usuario
     * en la tabla usuario y enviar un email de verificación
     * 
     * @param email
     * @param name
     * @param password
     * @throws IllegalArgumentException
     */
    public String register(String email, String name, String password, boolean isDoctor) {

        try {
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
                throw new UserAlreadyExistsException("Ya existe un usuario registrado con este email");
            }

            System.out.println(isDoctor);

            if (isDoctor) {
                DoctorRegistrationRequest request = new DoctorRegistrationRequest(
                        email,
                        name,
                        passwordEncoder.encode(password));
                doctorRegistrationRequestRepository.save(request);
                return "Debe esperar a que un administrador apruebe su solicitud de médico.";
            }

            User user = new User(email, name, passwordEncoder.encode(password));
            Long expiration = System.currentTimeMillis() + 1000 * 3600 * 24 * 3;
            String token = UUID.randomUUID().toString().replace("-", "");

            VerificationToken verificationToken = new VerificationToken(user, token, expiration);

            userRepository.save(user);
            verificationTokenRepository.save(verificationToken);
            this.emailTemplateService.sendConfirmationEmail(email, token);

            return "Revisa tu correo para confirmar tu cuenta antes de iniciar sesión.";
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Función de login que comprueba que las credenciales son correctas y devueleve
     * un token
     * 
     * @param email
     * @param password
     * @return String token
     */
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

    /**
     * Comprueba que el token recibido conincide con el almacenado en la base de
     * datos, verifica el correo correspondiente y elimina el token de la base de
     * datos
     * 
     * @param token
     * @return
     */
    public String confirmEmail(String token) {

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Token no válido"));

        if (verificationToken.getExpireDate() < System.currentTimeMillis()) {
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
