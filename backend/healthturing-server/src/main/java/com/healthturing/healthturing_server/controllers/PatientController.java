package com.healthturing.healthturing_server.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthturing.healthturing_server.dto.ApiResponseDTO;
import com.healthturing.healthturing_server.dto.DoctorDTO;
import com.healthturing.healthturing_server.dto.PatientCreateDTO;
import com.healthturing.healthturing_server.dto.PatientDTO;
import com.healthturing.healthturing_server.dto.PatientDataDTO;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.services.PatientService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/api/patient/user-patients/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PatientDTO>> getPatientsByUserId(@PathVariable Long userId) {
        List<PatientDTO> patientDtos = patientService.getPatientsByUserId(userId);
        return ResponseEntity.ok(patientDtos);
    }

    @GetMapping("/api/doctor/user-patients-doctor/{doctorId}")
    @PreAuthorize("hasRole('DOC')")
    public ResponseEntity<List<PatientDTO>> getPatientsByDoctorId(@PathVariable Long doctorId) {
        List<PatientDTO> patientDtos = patientService.getPatientsByDoctorId(doctorId);
        return ResponseEntity.ok(patientDtos);
    }

    @GetMapping("/api/patient/doctor/{patientId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DoctorDTO> getDoctorByPatientId(@PathVariable Long patientId) {
        DoctorDTO doctor = patientService.getDoctorByPatientId(patientId);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/api/patient/data/{patientId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PatientDataDTO> getAllDataPatientId(@PathVariable Long patientId) {
        PatientDataDTO patientDataDTO = patientService.getAllPatientData(patientId);
        return ResponseEntity.ok(patientDataDTO);
    }

    @PostMapping("/api/patient/create/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponseDTO> createPatientForUser(
            @PathVariable Long userId,
            @RequestBody @Valid PatientCreateDTO dto) {
        try {
            Patient patient = patientService.createPatientForUser(dto, userId);
            ApiResponseDTO response = new ApiResponseDTO("Paciente creado correctamente.", patient.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EntityNotFoundException ex) {
            ApiResponseDTO response = new ApiResponseDTO("Usuario no encontrado.", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (IllegalStateException ex) {
            ApiResponseDTO response = new ApiResponseDTO(ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception ex) {
            ApiResponseDTO response = new ApiResponseDTO("Ha ocurrido un error inesperado.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}