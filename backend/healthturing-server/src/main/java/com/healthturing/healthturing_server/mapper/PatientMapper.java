package com.healthturing.healthturing_server.mapper;

import java.util.List;
import java.util.Optional;

import com.healthturing.healthturing_server.dto.PatientDTO;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.models.User;

public class PatientMapper {
    
    public static PatientDTO toDto(Patient patient) {
        return new PatientDTO(
                    patient.getId(),
        patient.getName(),
        Optional.ofNullable(patient.getDoctor()).map(User::getId).orElse(null),
        Optional.ofNullable(patient.getDoctor()).map(User::getName).orElse(null)
        );
    }

    public static List<PatientDTO> toDtoList(List<Patient> patients) {
        return patients.stream()
                .map(PatientMapper::toDto)
                .toList();
    }
}
