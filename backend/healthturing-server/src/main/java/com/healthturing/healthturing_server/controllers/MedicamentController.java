package com.healthturing.healthturing_server.controllers;

import com.healthturing.healthturing_server.dto.MedicamentDTO;
import com.healthturing.healthturing_server.services.MedicamentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicaments")
public class MedicamentController {

    private final MedicamentService medicamentService;

    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping
    public ResponseEntity<List<MedicamentDTO>> getAllMedicaments() {
        return ResponseEntity.ok(medicamentService.getAllMedicaments());
    }

}
