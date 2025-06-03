package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.DoctorDTO;
import com.healthturing.healthturing_server.dto.PatientDTO;
import com.healthturing.healthturing_server.dto.PatientDataDTO;
import com.healthturing.healthturing_server.mapper.DoctorMapper;
import com.healthturing.healthturing_server.mapper.PatientDataMapper;
import com.healthturing.healthturing_server.mapper.PatientMapper;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.repositories.PatientRepository;

import jakarta.persistence.EntityNotFoundException;

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

    public DoctorDTO getDoctorByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
        return DoctorMapper.toDto(patient.getDoctor());
    }

    public PatientDataDTO getAllPatientData(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
        return PatientDataMapper.toDto(patient);
    }

}
