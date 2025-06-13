package com.healthturing.healthturing_server.dto;

import java.time.LocalDate;

import com.healthturing.healthturing_server.models.enums.BloodGroup;
import com.healthturing.healthturing_server.models.enums.Gender;
import com.healthturing.healthturing_server.models.enums.RhFactor;

import lombok.Data;

@Data
public class PatientCreateDTO {
    private String name;
    private String dni;
    private LocalDate dateOfBirth;
    private Gender gender;
    private BloodGroup bloodGroup;
    private RhFactor rhFactor;
    private String emergencyContact;
}
