package com.healthturing.healthturing_server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.repositories.IncompatibilityRepository;
import com.healthturing.healthturing_server.repositories.MedicamentRepository;
import com.healthturing.healthturing_server.repositories.SubstanceRepository;

@Service
public class MedicamentService {

    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired 
    private IncompatibilityRepository incompatibilityRepository;

    @Autowired
    private SubstanceRepository substanceRepository;

    public void createMedicament(){
        
    }
    
}
