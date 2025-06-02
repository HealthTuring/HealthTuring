package com.healthturing.healthturing_server.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.healthturing.healthturing_server.models.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    Page<Treatment> findByPatientId(Long patientId, Pageable pageable);
    List<Treatment> findByPatientId(Long id);

}
