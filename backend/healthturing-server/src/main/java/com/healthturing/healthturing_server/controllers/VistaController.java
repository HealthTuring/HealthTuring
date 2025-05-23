package com.healthturing.healthturing_server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {

    @GetMapping("/vista")
    public String mostrarVista(Model model) {
        model.addAttribute("mensaje", "Hola desde Thymeleaf!");
        return "vista"; // Este es el nombre del archivo HTML en /resources/templates
    }

    @GetMapping("/home")
    public String mostrarHome(Model model) {
        model.addAttribute("mensaje", "Redireccion a HOME");
        return "home"; // Este es el nombre del archivo HTML en /resources/templates
    }


}
