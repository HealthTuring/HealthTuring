package com.healthturing.healthturing_server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthturing.healthturing_server.dto.ApiResponseDTO;
import com.healthturing.healthturing_server.dto.TreatmentCreateDTO;
import com.healthturing.healthturing_server.dto.TreatmentUpdateDTO;
import com.healthturing.healthturing_server.models.Treatment;
import com.healthturing.healthturing_server.services.TreatmentService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

/**
 * RestController con endpoints accesibles solo por el doctor, y el
 * administrador
 * Protege las rutas definidas dentro de /doc, siendo necesario el rol Doc o
 * Admin de la cuenta logeada
 */
@RestController
@RequestMapping("/api/doctor")
@PreAuthorize("hasRole('DOC')")
public class DocController {

    private final TreatmentService treatmentService;

    public DocController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping("/create-treatment")
    public ResponseEntity<ApiResponseDTO> createTreatment(@RequestBody @Valid TreatmentCreateDTO dto) {
        try {
            Treatment treatment = treatmentService.createTreatment(dto);
            ApiResponseDTO response = new ApiResponseDTO("Tratamiento creado correctamente", treatment.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EntityNotFoundException ex) {
            ApiResponseDTO response = new ApiResponseDTO("No se encontró el paciente o medicamento: " + ex.getMessage(),
                    null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception ex) {
            ApiResponseDTO response = new ApiResponseDTO("Error al crear el tratamiento: " + ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete-treatment/{id}")
    public ResponseEntity<ApiResponseDTO> deleteTreatment(@PathVariable("id") Long treatmentId) {
        try {
            treatmentService.deleteTreatment(treatmentId);
            return ResponseEntity.ok(new ApiResponseDTO("Tratamiento eliminado correctamente", treatmentId));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDTO("No se encontró el tratamiento: " + ex.getMessage(), null));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDTO("Error al eliminar el tratamiento: " + ex.getMessage(), null));
        }
    }

    @PutMapping("/edit-treatment/{id}")
    public ResponseEntity<ApiResponseDTO> editTreatment(
            @PathVariable Long id,
            @RequestBody @Valid TreatmentUpdateDTO dto) {
        try {
            Treatment updated = treatmentService.updateTreatment(id, dto);
            return ResponseEntity.ok(
                    new ApiResponseDTO("Tratamiento editado correctamente", updated.getId()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDTO("No se encontró el tratamiento/paciente/medicamento: " + ex.getMessage(),null));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDTO("Error de validación: " + ex.getMessage(), null));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDTO("Error al editar el tratamiento: " + ex.getMessage(), null));
        }
    }

}
