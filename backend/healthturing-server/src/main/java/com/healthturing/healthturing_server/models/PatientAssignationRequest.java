package com.healthturing.healthturing_server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
@Entity
public class PatientAssignationRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dni;
    private String userName;
    private String email;
    private boolean approved = false;

    @OneToOne
    private Patient patient;

    public PatientAssignationRequest() {}

    public PatientAssignationRequest(Patient patient) {
        this.name = patient.getName();
        this.dni = patient.getDni();
        this.userName = patient.getUser().getName();
        this.email = patient.getUser().getEmail();
        this.patient = patient;
        this.approved = false;
    }
}
