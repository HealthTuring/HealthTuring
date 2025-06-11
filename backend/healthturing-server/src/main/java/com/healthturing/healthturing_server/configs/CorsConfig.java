package com.healthturing.healthturing_server.configs;
 
 import java.util.List;

import org.springframework.beans.factory.annotation.Value;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
 /**
  * Configuración de Cors
  * Toma de las variables de entorno el cors habilitado y permite de este que se hagan solicitudes Get, Post, Put, Delete, Options con cualquier header
  */
 @Configuration
 public class CorsConfig {
 
   @Value("${cors.allowedOrigins}")
   private String allowedOrigins;
 
   @Bean
   public WebMvcConfigurer corsConfigurer() {
     return new WebMvcConfigurer() {
       @Override
       public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**")
             .allowedOrigins(allowedOrigins.split(","))
             .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
             .allowedHeaders("*")
             .allowCredentials(true);
       }
     };

     
   }


   @Bean
    public CorsConfigurationSource corsConfigurationSource(
        @Value("${cors.allowedOrigins}") String allowedOrigins) {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(allowedOrigins.split(",")));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
 }
