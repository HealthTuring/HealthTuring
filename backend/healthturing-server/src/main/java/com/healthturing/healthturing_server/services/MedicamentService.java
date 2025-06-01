package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.MedicamentDTO;
import com.healthturing.healthturing_server.mapper.MedicamentMapper;
import com.healthturing.healthturing_server.repositories.MedicamentRepository;

@Service
public class MedicamentService {

    private final MedicamentRepository medicamentRepository;

    public MedicamentService(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;
    }

    public List<MedicamentDTO> getAllMedicaments() {
        return MedicamentMapper.toDtoList(medicamentRepository.findAll());
    }

}
