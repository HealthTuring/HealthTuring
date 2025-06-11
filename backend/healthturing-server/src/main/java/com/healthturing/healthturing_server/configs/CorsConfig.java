package com.healthturing.healthturing_server.configs;
 
 import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
 import jakarta.annotation.PostConstruct;
 
 /**
  * Configuración de Cors
  * Toma de las variables de entorno el cors habilitado y permite de este que se hagan solicitudes Get, Post, Put, Delete, Options con cualquier header
  */
 @Configuration
 public class CorsConfig {
 
   @Value("${cors.allowedOrigins}")
private String allowedOrigins; // una lista separada por coma

@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    // Convertir string en lista
    List<String> origins = Arrays.stream(allowedOrigins.split(","))
        .map(String::trim)
        .toList();

    configuration.setAllowedOrigins(origins);
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}

@PostConstruct
public void logCorsOrigins() {
    System.out.println("CORS Origins loaded: " + allowedOrigins);
}
 }
