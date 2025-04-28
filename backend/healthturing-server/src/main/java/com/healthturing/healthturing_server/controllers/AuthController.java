package com.healthturing.healthturing_server.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthturing.healthturing_server.dto.LoginRequestDTO;
import com.healthturing.healthturing_server.dto.RegisterRequestDTO;
import com.healthturing.healthturing_server.exceptions.EmailEmitErrorException;
import com.healthturing.healthturing_server.exceptions.EmailNotConfirmedException;
import com.healthturing.healthturing_server.exceptions.InvalidTokenException;
import com.healthturing.healthturing_server.exceptions.UserAlreadyExistsException;
import com.healthturing.healthturing_server.exceptions.UserNotFoundException;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO request) {
        try {
            authService.register(request.getEmail(), request.getName(), request.getPassword());
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        } catch (EmailEmitErrorException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al enviar el email de confirmación");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de registro inválidos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO request) {
        try {
            String token = authService.login(request.getEmail(), request.getPassword());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(response);

        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Email o contraseña incorrectos"));
        } catch (EmailNotConfirmedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Debes confirmar tu correo antes de iniciar sesión."));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales incorrectas"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error interno del servidor"));
        }
    }

    @GetMapping("/check-status")
    public Map<String, Object> checkAuthStatus(@RequestBody User user) {
        return authService.checkAuthStatus(user);
    }

    @PutMapping("/email-confirmation")
    public ResponseEntity<String> emailConfirm(@RequestBody String token) {
        try {
            authService.confirmEmail(token);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                    .body("Correo Validado");
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

}
