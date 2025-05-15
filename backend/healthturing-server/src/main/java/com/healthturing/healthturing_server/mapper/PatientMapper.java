package com.healthturing.healthturing_server.mapper;

import java.util.List;

import com.healthturing.healthturing_server.dto.PatientDTO;
import com.healthturing.healthturing_server.models.Patient;

public class PatientMapper {
    
    public static PatientDTO toDto(Patient patient) {
        return new PatientDTO(patient.getId(), patient.getName());
    }

    public static List<PatientDTO> toDtoList(List<Patient> patients) {
        return patients.stream()
                .map(PatientMapper::toDto)
                .toList();
    }
}
