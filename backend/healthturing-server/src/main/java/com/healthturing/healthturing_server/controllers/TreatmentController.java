package com.healthturing.healthturing_server.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthturing.healthturing_server.dto.TreatmentDTO;
import com.healthturing.healthturing_server.services.TreatmentService;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }
    @GetMapping("/{patientId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getTreatmentsByPatientId(@PathVariable Long patientId) {
        try {
            List<TreatmentDTO> treatments = treatmentService.getTreatmentsByPatientId(patientId);
            if (treatments == null || treatments.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron tratamientos para el paciente con ID: " + patientId);
            }
            return ResponseEntity.ok(treatments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los tratamientos: " + e.getMessage());
        }
    }
}