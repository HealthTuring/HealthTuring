package com.healthturing.healthturing_server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PatientAssignationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dni;
    private String userName;
    private String email;
    private boolean approved = false;
    
    public PatientAssignationRequest(String name, String dni, String userName, String email, boolean approved) {
        this.name = name;
        this.dni = dni;
        this.userName = userName;
        this.email = email;
        this.approved = approved;
    }

    
}
