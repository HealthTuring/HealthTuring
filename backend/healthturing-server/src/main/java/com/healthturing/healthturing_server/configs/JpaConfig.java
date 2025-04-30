package com.healthturing.healthturing_server.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuración de repositorios Jpa
 * Permite la creación de repositorios JPA
 */
@Configuration
@EnableJpaRepositories
public class JpaConfig {
}