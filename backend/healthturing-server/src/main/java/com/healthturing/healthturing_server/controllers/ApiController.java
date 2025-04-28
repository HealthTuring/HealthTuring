package com.healthturing.healthturing_server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/prueba")
    public String prueba() {
        return "http://localhost:8080/swagger-ui/index.html#/api-controller/prueba";
    }

    @GetMapping("/hola")
    public String holaMundo() {
        return "¡Hola mundo desde Spring Boot!";
    }
    
}
