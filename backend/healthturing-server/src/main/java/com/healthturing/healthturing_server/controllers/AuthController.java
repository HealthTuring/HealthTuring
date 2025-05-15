package com.healthturing.healthturing_server.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthturing.healthturing_server.dto.LoginRequestDTO;
import com.healthturing.healthturing_server.dto.RegisterRequestDTO;
import com.healthturing.healthturing_server.exceptions.EmailNotConfirmedException;
import com.healthturing.healthturing_server.exceptions.EmailSendingException;
import com.healthturing.healthturing_server.exceptions.InvalidJwtException;
import com.healthturing.healthturing_server.exceptions.InvalidPasswordException;
import com.healthturing.healthturing_server.exceptions.InvalidTokenException;
import com.healthturing.healthturing_server.exceptions.SamePasswordException;
import com.healthturing.healthturing_server.exceptions.TokenExpiredException;
import com.healthturing.healthturing_server.exceptions.UserAlreadyExistsException;
import com.healthturing.healthturing_server.exceptions.UserNotFoundException;
import com.healthturing.healthturing_server.services.AuthService;
import com.healthturing.healthturing_server.services.PasswordResetService;

import jakarta.validation.Valid;


/**
 * RestController con endpoints accesibles para el procedimiento de autenticación completo
 * Sirve de entrada para login, register, email-confirmation, y devuelve el token en caso de login
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    public AuthController(AuthService authService, PasswordResetService passwordResetService) {
        this.authService = authService;
        this.passwordResetService = passwordResetService;
    }   

    /**
     * Registra un nuevo usuario usando el método register de AuthService
     * @param request Usuario a registrar -> Estructura request establecida mediante RegisterRequestDto
     * @return ResponseEntity
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO request) {
        try {
            authService.register(request.getEmail(), request.getName(), request.getPassword());
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EmailSendingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    /**
     * Verifica el inicio de sesión respecto al email y contraseña recibidos y devuelve el token de inicio de sesión
     * @param request Email/Contraseña ->Estructura request de LoginRequestDto.java
     * @return String token
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
        try {
            String token = authService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                    .body(token);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EmailNotConfirmedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }


    /**
     * Verifica que el token recibido corresponda a un correo para activarlo y pueda realizar el inicio de sesión
     * @param token 
     * @return ResponseEntity
     */
    @PutMapping("/email-confirmation")
    public ResponseEntity<String> emailConfirm(@RequestBody String token) {
        try {
            authService.confirmEmail(token);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                    .body("Correo electrónico confirmado");
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PostMapping("/forget-password")
    public ResponseEntity<String> requestPasswordReset(@RequestBody String email) {
        try {
            String token = passwordResetService.sendPasswordResetEmail(email);
            return ResponseEntity.ok(token);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EmailSendingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
  
    @PutMapping("/reset-password/{token}")
    public ResponseEntity<String> resetPassword(@PathVariable String token, @RequestBody String newPassword) {
        try {
            passwordResetService.resetPassword(token, newPassword);
            return ResponseEntity.ok("Contraseña restablecida correctamente");
        } catch (TokenExpiredException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El tiempo de restablecimiento de contraseña ha expirado.");
        } catch (InvalidJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (SamePasswordException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }
    
}
