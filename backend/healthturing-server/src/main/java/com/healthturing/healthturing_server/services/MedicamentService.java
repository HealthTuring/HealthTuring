package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.models.Medicament;
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

    public List<Medicament> getAllMedicaments() {
        return medicamentRepository.findAll();
    }

    public Medicament getMedicamentById(Long id) {
        return medicamentRepository.findById(id).orElse(null);
    }

    public Medicament saveMedicament(Medicament medicament) {
        
        Medicament basemed = medicamentRepository.findById(medicament.getId()).get();

        basemed.setCommonName(medicament.getCommonName());


        return medicamentRepository.save(basemed);
    }

    public Medicament updateMedicament(Medicament medicament) {
        return medicamentRepository.save(medicament);
    }

    public void deleteMedicament(Long id) {
        medicamentRepository.deleteById(id);
    }
    
}
