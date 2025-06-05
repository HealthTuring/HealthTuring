package com.healthturing.healthturing_server.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthturing.healthturing_server.dto.AppointmentDTO;
import com.healthturing.healthturing_server.dto.AppointmentRequestDTO;
import com.healthturing.healthturing_server.exceptions.AppointmentLimitException;
import com.healthturing.healthturing_server.services.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
@PreAuthorize("isAuthenticated()")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{patientId}")
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

    @GetMapping("/doctor/{doctorId}/available-slots")
    public ResponseEntity<?> getAvailableSlots(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            if (doctorId == null || date == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El id del doctor y la fecha son requeridos.");
            }
            List<LocalTime> availableSlots = appointmentService.getAvailableSlotsForDoctor(doctorId, date);
            return ResponseEntity.ok(availableSlots);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveAppointment(@RequestBody AppointmentRequestDTO request) {
        try {
            appointmentService.createAppointment(
                    request.patientId,
                    request.doctorId,
                    request.date,
                    request.startTime,
                    request.reason);
            return ResponseEntity.status(HttpStatus.CREATED).body("La cita se creo con éxito");
        } catch (AppointmentLimitException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reservando cita: " + e.getMessage());
        }
    }
}
