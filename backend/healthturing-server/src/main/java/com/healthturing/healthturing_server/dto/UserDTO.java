package com.healthturing.healthturing_server.dto;

/**
 * DTO User
 * Especifica la información del usuario que se tiene que emitir al front
 * Aún no está implementado del todo
 */
public class UserDTO {

    private String name;

    private String email;



    public UserDTO(String name, String email) {
        this.name = name;

        this.email = email;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    

}