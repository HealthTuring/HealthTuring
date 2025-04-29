package com.healthturing.healthturing_server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@PreAuthorize("hasAnyRole('DOC')")
@RestController
@RequestMapping("/doc")
public class DocController {
    
    @GetMapping("/prueba")
    public String pruebaDoc() {
        return "Funciona controller doc";
    }
    
}
