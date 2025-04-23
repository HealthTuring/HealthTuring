package com.healthturing.healthturing_server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthturing.healthturing_server.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(String name);
}
