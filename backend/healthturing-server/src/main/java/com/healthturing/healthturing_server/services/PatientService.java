package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.DoctorDTO;
import com.healthturing.healthturing_server.dto.PatientCreateDTO;
import com.healthturing.healthturing_server.dto.PatientDTO;
import com.healthturing.healthturing_server.dto.PatientDataDTO;
import com.healthturing.healthturing_server.mapper.DoctorMapper;
import com.healthturing.healthturing_server.mapper.PatientDataMapper;
import com.healthturing.healthturing_server.mapper.PatientMapper;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.models.PatientAssignationRequest;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.enums.Role;
import com.healthturing.healthturing_server.repositories.PatientAssignationRequestRepository;
import com.healthturing.healthturing_server.repositories.PatientRepository;
import com.healthturing.healthturing_server.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PatientAssignationRequestRepository assignationRepository;

    public PatientService(PatientRepository patientRepository, UserRepository userRepository, PatientAssignationRequestRepository assignationRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.assignationRepository = assignationRepository;
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

@Transactional
public Patient createPatientForUser(PatientCreateDTO dto, Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

    if (!user.getRole().equals(Role.ROLE_USER))
        throw new IllegalArgumentException("El propietario debe ser un usuario");

    int count = patientRepository.countByUserId(userId);
    if (count >= 3) throw new IllegalStateException("Máximo de 3 pacientes por usuario.");

    if (patientRepository.existsByDni(dto.getDni()))
        throw new IllegalStateException("El DNI ya está registrado en otro paciente.");

    Patient patient = new Patient();
    patient.setName(dto.getName());
    patient.setDni(dto.getDni());
    patient.setDateOfBirth(dto.getDateOfBirth());
    patient.setGender(dto.getGender());           
    patient.setBloodGroup(dto.getBloodGroup());
    patient.setRhFactor(dto.getRhFactor());
    patient.setEmergencyContact(dto.getEmergencyContact());
    patient.setUser(user);
    patient.setDoctorAssigned(false);
    patient.setDoctor(null);

    Patient savedPatient = patientRepository.save(patient);

    PatientAssignationRequest request = new PatientAssignationRequest(savedPatient);
    assignationRepository.save(request);

    return savedPatient;
}

}
