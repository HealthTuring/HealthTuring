package com.healthturing.healthturing_server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healthturing.healthturing_server.models.Incompatibility;
import com.healthturing.healthturing_server.models.Medicament;
import com.healthturing.healthturing_server.models.Substance;
import com.healthturing.healthturing_server.services.IncompatibilityService;
import com.healthturing.healthturing_server.services.MedicamentService;
import com.healthturing.healthturing_server.services.SubstanceService;

@Controller
@RequestMapping("/admin/incompatibilities")
public class IncompatibilityController {

    @Autowired
    private IncompatibilityService incompatibilityService;

    @Autowired
    private MedicamentService medicamentService;

    @Autowired
    private SubstanceService substanceService;

    @GetMapping
    public String listIncompatibilities(Model model) {
        model.addAttribute("incompatibilities", incompatibilityService.getAllIncompatibilities());
        return "incompatibilities/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        // Inicializar campos necesarios
        Incompatibility incompatibility = new Incompatibility();
        incompatibility.setMedicament(new Medicament());  
        incompatibility.setIncompatibleMedicament(new Medicament());
        incompatibility.setIncompatibleSubstance(new Substance());
        model.addAttribute("incompatibility", incompatibility);
        model.addAttribute("medicaments", medicamentService.getAllMedicaments());
        model.addAttribute("substances", substanceService.getAllSubstances());
        return "incompatibilities/create";
    }

    @PostMapping
    public String createIncompatibility(@ModelAttribute Incompatibility incompatibility) {
        incompatibilityService.saveIncompatibility(incompatibility);
        return "redirect:/admin/incompatibilities";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Incompatibility incompatibility = incompatibilityService.getIncompatibilityById(id);
        model.addAttribute("incompatibility", incompatibility);
        model.addAttribute("medicaments", medicamentService.getAllMedicaments());
        model.addAttribute("substances", substanceService.getAllSubstances());
        return "incompatibilities/edit";
    }

    @PostMapping("/{id}")
    public String updateIncompatibility(@PathVariable Long id, @ModelAttribute Incompatibility incompatibility) {
        incompatibilityService.updateIncompatibility(id, incompatibility);
        return "redirect:/admin/incompatibilities";
    }

    @GetMapping("/delete/{id}")
    public String deleteIncompatibility(@PathVariable Long id) {
        incompatibilityService.deleteIncompatibility(id);
        return "redirect:/admin/incompatibilities";
    }

    @GetMapping("/view/{id}")
    public String viewIncompatibility(@PathVariable Long id, Model model) {
        model.addAttribute("incompatibility", incompatibilityService.getIncompatibilityById(id));
        return "incompatibilities/view";
    }
}