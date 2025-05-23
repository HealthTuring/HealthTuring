package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.TreatmentDTO;
import com.healthturing.healthturing_server.mapper.TreatmentMapper;
import com.healthturing.healthturing_server.models.Treatment;
import com.healthturing.healthturing_server.repositories.TreatmentRepository;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<TreatmentDTO> getTreatmentsByPatientId(Long patientId) {
        List<Treatment> treatments = treatmentRepository.findByPatientId(patientId);
        return TreatmentMapper.toDtoList(treatments); 
    }
    
}
