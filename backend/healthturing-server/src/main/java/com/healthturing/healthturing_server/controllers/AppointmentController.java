package com.healthturing.healthturing_server.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthturing.healthturing_server.dto.AppointmentDTO;
import com.healthturing.healthturing_server.services.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{patientId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAppointmentsByPatientId(@PathVariable Long patientId) {
        try {
            List<AppointmentDTO> appointments = appointmentService.getAppointmentsByPatientId(patientId);
            if (appointments == null || appointments.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron citas para el paciente con ID: " + patientId);
            }
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las citas: " + e.getMessage());
        }
    }
    
}
