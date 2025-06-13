package com.healthturing.healthturing_server.mapper;

import java.util.List;

import com.healthturing.healthturing_server.dto.IncompabilityDTO;
import com.healthturing.healthturing_server.models.Incompatibility;

public class IncompabilityMapper {
    
    public static IncompabilityDTO toDto(Incompatibility incompatibility) {
        return new IncompabilityDTO(
            incompatibility.getId(),
            incompatibility.getIncompatibleMedicament() != null ? incompatibility.getIncompatibleMedicament().getCommonName() : null,
            incompatibility.getIncompatibleSubstance() != null ? incompatibility.getIncompatibleSubstance().getName() : null
        );
    }
    
    public static List<IncompabilityDTO> toDtoList(List<Incompatibility> incompatibilities) {
        return incompatibilities.stream()
                .map(IncompabilityMapper::toDto)
                .toList();
    }
}
