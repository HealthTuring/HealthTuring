package com.healthturing.healthturing_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO de request Register
 * Especifica la estructura que tiene que recibir la petición a Register en AuthController para realizar el registro de nuevo usuario
 */
public class RegisterRequestDTO {
    private String email;
    private String name;
    private String password;
    
    @JsonProperty("isDoctor") 
    private boolean isDoctor;

    public RegisterRequestDTO() {}

    public RegisterRequestDTO(String email, String name, String password, boolean isDoctor) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.isDoctor = isDoctor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean isDoctor) {
        this.isDoctor = isDoctor;
    }

}
