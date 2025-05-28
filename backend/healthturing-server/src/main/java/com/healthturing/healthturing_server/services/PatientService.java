package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.PatientDTO;
import com.healthturing.healthturing_server.mapper.PatientMapper;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.repositories.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> getPatientsByUserId(Long userId) {
        List<Patient> patients = patientRepository.findByUserId(userId);
        return PatientMapper.toDtoList(patients);
    }

    public List<PatientDTO> getPatientsByDoctorId(Long userId) {
        List<Patient> patients = patientRepository.findByDoctorId(userId);
        return PatientMapper.toDtoList(patients);
    }
    
}
