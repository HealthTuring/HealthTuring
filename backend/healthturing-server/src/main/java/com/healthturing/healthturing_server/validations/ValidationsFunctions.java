package com.healthturing.healthturing_server.validations;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/**
 * Clase con los métodos de validación empleados
 */
@Component
public class ValidationsFunctions {

    /**
     * Método de validación de email
     * @param email
     * @return
     */
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    /**
     * Método de validación de contraseña(formato)
     * @param password
     * @return
     */
    public boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

}
