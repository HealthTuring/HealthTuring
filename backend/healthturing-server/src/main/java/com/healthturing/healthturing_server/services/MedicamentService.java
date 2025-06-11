package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.MedicamentDTO;
import com.healthturing.healthturing_server.mapper.MedicamentMapper;
import com.healthturing.healthturing_server.models.Medicament;
import com.healthturing.healthturing_server.repositories.MedicamentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicamentService {

    private final MedicamentRepository medicamentRepository;

    public List<Medicament> getAllMedicamentsAdmin() {
        return medicamentRepository.findAll();
    }

    public Medicament getMedicamentById(Long id) {
        return medicamentRepository.findById(id).orElse(null);
    }

    public Medicament updateMedicament(Medicament medicament) {
        Medicament basemed = medicamentRepository.findById(medicament.getId()).get();
        basemed.setCommonName(medicament.getCommonName());
        return medicamentRepository.save(basemed);
    }


    public Medicament createMedicament(Medicament medicament){
        return medicamentRepository.save(medicament);
    }

    public void deleteMedicament(Long id) {
        medicamentRepository.deleteById(id);
    }

    public List<MedicamentDTO> getAllMedicaments() {
        return MedicamentMapper.toDtoList(medicamentRepository.findAll());
    }
    
}
