package com.healthturing.healthturing_server.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * RestController con endpoints accesibles por un usuario verificado correctamente
 * Protege las rutas definidas dentro de /user con el token de inicio de sesión, pueden acceder admin, doc y user a esta parte
 */
@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DOC')")
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/pruebaAd")
    public String pruebaAd() {
        return "Funciona Controller User";
    }
    
}