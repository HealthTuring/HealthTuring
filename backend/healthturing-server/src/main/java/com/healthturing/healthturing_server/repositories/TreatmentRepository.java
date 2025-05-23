package com.healthturing.healthturing_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthturing.healthturing_server.models.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    List<Treatment> findByPatientId(Long patientId);

}
