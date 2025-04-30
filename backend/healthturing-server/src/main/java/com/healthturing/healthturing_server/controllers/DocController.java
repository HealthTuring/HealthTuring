package com.healthturing.healthturing_server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * RestController con endpoints accesibles solo por el doctor, y el administrador
 * Protege las rutas definidas dentro de /doc, siendo necesario el rol Doc o Admin de la cuenta logeada
 */
@PreAuthorize("hasAnyRole('DOC')")
@RestController
@RequestMapping("/doc")
public class DocController {
    
    @GetMapping("/prueba")
    public String pruebaDoc() {
        return "Funciona controller doc";
    }
    
}
