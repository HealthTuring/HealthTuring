package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.models.Substance;
import com.healthturing.healthturing_server.repositories.SubstanceRepository;

@Service
public class SubstanceService {
    	
    @Autowired
    private SubstanceRepository substanceRepository;

    public List<Substance> getAllSubstances() {
        return substanceRepository.findAll();
    }

   
    public Substance getSubstanceById(Long id) {
        return substanceRepository.findById(id).orElse(null);
    }

   
    public Substance saveSubstance(Substance substance) {
        return substanceRepository.save(substance);
    }

   
    public Substance updateSubstance(Long id, Substance substance) {
        Substance existing = substanceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Substance no encontrado con id " + id));
        existing.setName(substance.getName());
        return substanceRepository.save(existing);
    }

   
    public void deleteSubstance(Long id) {
        substanceRepository.deleteById(id);
    }
    
}
