package com.healthturing.healthturing_server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healthturing.healthturing_server.models.Medicament;
import com.healthturing.healthturing_server.services.MedicamentService;

@Controller
@RequestMapping("/admin/medicaments")
public class MedAdRestController {
    private final MedicamentService medicamentService;

    public MedAdRestController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping
    public String listMedicaments(Model model) {
        model.addAttribute("medicaments", medicamentService.getAllMedicaments());
        return "medicaments/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("medicament", new Medicament());
        return "medicaments/create";
    }

    @PostMapping
    public String createMedicament(@ModelAttribute Medicament medicament) {
        medicamentService.createMedicament(medicament);
        return "redirect:/admin/medicaments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Medicament medicament = medicamentService.getMedicamentById(id);
        model.addAttribute("medicament", medicament);
        return "medicaments/edit";
    }

    @PostMapping("/{id}")
    public String updateMedicament(@PathVariable Long id, @ModelAttribute Medicament medicament) {
        medicament.setId(id);
        medicamentService.updateMedicament(medicament);
        return "redirect:/admin/medicaments";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicament(@PathVariable Long id) {
        medicamentService.deleteMedicament(id);
        return "redirect:/admin/medicaments";
    }

    @GetMapping("/view/{id}")
    public String viewMedicament(@PathVariable Long id, Model model) {
        Medicament medicament = medicamentService.getMedicamentById(id);
        model.addAttribute("medicament", medicament);
        return "medicaments/view";
    }
}
