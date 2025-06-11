package com.healthturing.healthturing_server.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.exceptions.SamePasswordException;
import com.healthturing.healthturing_server.exceptions.InvalidPasswordException;
import com.healthturing.healthturing_server.exceptions.TokenExpiredException;
import com.healthturing.healthturing_server.exceptions.UserNotFoundException;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.repositories.UserRepository;
import com.healthturing.healthturing_server.validations.ValidationsFunctions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailTemplateService emailTemplateService;
    private final ValidationsFunctions validationsFunctions;

    /**
     * Envía un email al usuario con un token (expira en 30min) para restablecer la
     * contaseña.
     * @param email
     * @return
     */
    public String sendPasswordResetEmail(String email) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("No existe nigún usuario registrado con este email");
        }

        User user = userOptional.get();
        String token = jwtService.generateTokenPassword(user);

        emailTemplateService.sendResetPasswordEmail(email, token);
        return token;

    }

    /**
     * Comprueba que el token y la nueva contraseña sean válidos,
     * y encripta y guarda la nueva contraseña del usuario.
     * @param token
     * @param newPassword
     */
    public void resetPassword(String token, String newPassword) {
        if (jwtService.isTokenExpired(token)) {
            throw new TokenExpiredException("El token de restablecimiento de contraseña ha expirado.");
        }

        int userId = jwtService.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado."));

        if (!validationsFunctions.isValidPassword(newPassword)) {
            throw new InvalidPasswordException("La contraseña no cumple con los requisitos de seguridad.");
        }

        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new SamePasswordException("La nueva contraseña no puede ser idéntica a la anterior.");
        }

        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

}
