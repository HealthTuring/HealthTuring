package com.healthturing.healthturing_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthturing.healthturing_server.models.Incompatibility;

@Repository
public interface IncompatibilityRepository extends JpaRepository<Incompatibility, Long>{
    

}
