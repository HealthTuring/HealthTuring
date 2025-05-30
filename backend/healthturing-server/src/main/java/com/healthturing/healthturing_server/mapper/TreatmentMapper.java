package com.healthturing.healthturing_server.mapper;

import java.util.List;

import com.healthturing.healthturing_server.dto.TreatmentDTO;
import com.healthturing.healthturing_server.models.Treatment;

public class TreatmentMapper {

    public static TreatmentDTO toDto(Treatment treatment) {
        return new TreatmentDTO(
            treatment.getId(), 
            treatment.getMedicament().getCommonName(), 
            treatment.getName(),
            treatment.getDescription(),
            treatment.getMedicament().getStrength(),
            treatment.getMedicament().getDosageForm(),
            treatment.getDosesPerPeriod(),
            treatment.getMedicament().getDrugRoute(),
            treatment.getDuration(),
            treatment.getStartDate(),
            treatment.getEndDate(),
            treatment.getMedicament().getProspectUrl(),
            treatment.getMedicament().getIncompatibilities().stream()
                .map(IncompabilityMapper::toDto)
                .toList()
        );       
    }

    public static List<TreatmentDTO> toDtoList(List<Treatment> treatments) {
        return treatments.stream()
                .map(TreatmentMapper::toDto)
                .toList();
    }
    
}
