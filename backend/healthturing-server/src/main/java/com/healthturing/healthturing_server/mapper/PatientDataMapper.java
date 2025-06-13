package com.healthturing.healthturing_server.mapper;

import java.util.List;
import java.util.Optional;

import com.healthturing.healthturing_server.dto.PatientDataDTO;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.models.User;

public class PatientDataMapper {
        public static PatientDataDTO toDto(Patient patient) {
        return new PatientDataDTO(
            patient.getId(), 
            patient.getName(),
            patient.getDni(),
            patient.getDateOfBirth(),
            patient.getGender(),
            patient.getBloodGroup(),
            patient.getRhFactor(),
            patient.getEmergencyContact(),
            Optional.ofNullable(patient.getDoctor()).map(User::getName).orElse(null)
        );
    }

    public static List<PatientDataDTO> toDtoList(List<Patient> patients) {
        return patients.stream()
                .map(PatientDataMapper::toDto)
                .toList();
    }
}
