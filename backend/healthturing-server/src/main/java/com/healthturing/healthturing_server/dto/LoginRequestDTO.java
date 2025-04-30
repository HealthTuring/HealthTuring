package com.healthturing.healthturing_server.dto;

/**
 * DTO de request Login
 * Especifica la estructura que tiene que recibir la petición a login en AuthController para realizar el inicio de sesión
 */
public class LoginRequestDTO {
    private String email;
    private String password;

    public LoginRequestDTO() {}

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
