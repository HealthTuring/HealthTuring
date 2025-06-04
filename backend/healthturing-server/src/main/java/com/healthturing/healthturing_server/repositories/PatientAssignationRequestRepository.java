package com.healthturing.healthturing_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthturing.healthturing_server.models.PatientAssignationRequest;

public interface PatientAssignationRequestRepository extends JpaRepository<PatientAssignationRequest, Long> {
    List<PatientAssignationRequest> findByApprovedFalse();
}
