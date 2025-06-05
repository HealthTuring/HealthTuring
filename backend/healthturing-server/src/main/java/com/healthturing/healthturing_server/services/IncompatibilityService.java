package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.models.Incompatibility;
import com.healthturing.healthturing_server.models.Medicament;
import com.healthturing.healthturing_server.models.Substance;
import com.healthturing.healthturing_server.repositories.IncompatibilityRepository;
import com.healthturing.healthturing_server.repositories.MedicamentRepository;
import com.healthturing.healthturing_server.repositories.SubstanceRepository;

@Service
public class IncompatibilityService {
    
    @Autowired
    private IncompatibilityRepository incompatibilityRepository;

    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired
    private SubstanceRepository substanceRepository;

   
    public List<Incompatibility> getAllIncompatibilities() {
        return incompatibilityRepository.findAll();
    }

   
    public Incompatibility getIncompatibilityById(Long id) {
        return incompatibilityRepository.findById(id).orElse(null);
    }

   


    
    public Incompatibility saveIncompatibility(Incompatibility incompatibility) {
        // Recuperar el medicamento completo
        Long medicamentId = incompatibility.getMedicament().getId();
        Medicament medicament = medicamentRepository.findById(medicamentId)
                            .orElseThrow(() -> new IllegalArgumentException("Medicament no encontrado"));
        incompatibility.setMedicament(medicament);

        if (incompatibility.getIncompatibleMedicament() != null && incompatibility.getIncompatibleMedicament().getId() != null) {
            Long incompatibleMedId = incompatibility.getIncompatibleMedicament().getId();
            Medicament incompatibleMed = medicamentRepository.findById(incompatibleMedId)
                            .orElseThrow(() -> new IllegalArgumentException("Incompatible Medicament no encontrado"));
            incompatibility.setIncompatibleMedicament(incompatibleMed);
        } else {
            incompatibility.setIncompatibleMedicament(null);
        }

        if (incompatibility.getIncompatibleSubstance() != null && incompatibility.getIncompatibleSubstance().getId() != null) {
            Long incompatibleSubstanceId = incompatibility.getIncompatibleSubstance().getId();
            Substance incompatibleSubstance = substanceRepository.findById(incompatibleSubstanceId)
                            .orElseThrow(() -> new IllegalArgumentException("Incompatible Substance no encontrado"));
            incompatibility.setIncompatibleSubstance(incompatibleSubstance);
        } else {
            incompatibility.setIncompatibleSubstance(null);
        }

        return incompatibilityRepository.save(incompatibility);
    }




   
    public Incompatibility updateIncompatibility(Long id, Incompatibility incompatibility) {
        Incompatibility existing = incompatibilityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Incompatibility no encontrada con id " + id));
        existing.setMedicament(incompatibility.getMedicament());
        existing.setIncompatibleMedicament(incompatibility.getIncompatibleMedicament());
        existing.setIncompatibleSubstance(incompatibility.getIncompatibleSubstance());
        return incompatibilityRepository.save(existing);
    }

   
    public void deleteIncompatibility(Long id) {
        incompatibilityRepository.deleteById(id);
    }
}
