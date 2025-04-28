package com.healthturing.healthturing_server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@PreAuthorize("hasAnyRole('ADMIN')")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/pruebaAd")
    public String pruebaAd() {
        return "Funciona Controller Admin";
    }
    
}
