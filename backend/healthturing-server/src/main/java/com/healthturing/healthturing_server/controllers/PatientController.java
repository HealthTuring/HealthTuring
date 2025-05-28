package com.healthturing.healthturing_server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.healthturing.healthturing_server.dto.DoctorDTO;
import com.healthturing.healthturing_server.dto.PatientDTO;
import com.healthturing.healthturing_server.services.PatientService;

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
}