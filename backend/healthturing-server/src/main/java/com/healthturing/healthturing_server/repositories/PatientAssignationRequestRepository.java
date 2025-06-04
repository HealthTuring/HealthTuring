package com.healthturing.healthturing_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthturing.healthturing_server.models.PatientAssignationRequest;

public interface PatientAssignationRequestRepository extends JpaRepository<PatientAssignationRequest, Long> {
    
}
