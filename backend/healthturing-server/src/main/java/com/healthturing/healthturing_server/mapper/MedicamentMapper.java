package com.healthturing.healthturing_server.mapper;

import java.util.List;

import com.healthturing.healthturing_server.dto.MedicamentDTO;
import com.healthturing.healthturing_server.models.Medicament;

public class MedicamentMapper {
    
        public static MedicamentDTO toDto(Medicament medicament) {
        return new MedicamentDTO(
            medicament.getId(), 
            medicament.getCommonName(),
            medicament.getDosageForm(),
            medicament.getDrugRoute(),
            medicament.getStrength()
        );       
    }

    public static List<MedicamentDTO> toDtoList(List<Medicament> medicament) {
        return medicament.stream()
                .map(MedicamentMapper::toDto)
                .toList();
    }
}
