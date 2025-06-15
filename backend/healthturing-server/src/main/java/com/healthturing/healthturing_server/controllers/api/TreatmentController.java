package com.healthturing.healthturing_server.controllers.api;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // SIN paginación 
    @GetMapping("/{patientId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getTreatmentsByPatientId(@PathVariable Long patientId) {
        try {
            List<TreatmentDTO> treatments = treatmentService.getTreatmentsByPatientId(patientId);
            return ResponseEntity.ok(treatments == null ? Collections.emptyList() : treatments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error al obtener los tratamientos: " + e.getMessage()));
        }
    }

    // CON paginación 
    @GetMapping("/{patientId}/paged")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getTreatmentsByPatientIdPaged(
            @PathVariable Long patientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        try {
            Page<TreatmentDTO> treatmentsPage = treatmentService.getTreatmentsByPatientIdPaged(patientId, page, size);
            return ResponseEntity.ok(treatmentsPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error al obtener los tratamientos: " + e.getMessage()));
        }
    }
}