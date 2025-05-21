package com.healthturing.healthturing_server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthturing.healthturing_server.dto.PatientDTO;
import com.healthturing.healthturing_server.services.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/user-patients/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PatientDTO>> getPatientsByUserId(@PathVariable Long userId) {
        List<PatientDTO> patientDtos = patientService.getPatientsByUserId(userId);
        return ResponseEntity.ok(patientDtos);
    }
}