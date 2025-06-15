package com.healthturing.healthturing_server.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healthturing.healthturing_server.models.Substance;
import com.healthturing.healthturing_server.services.SubstanceService;

@Controller
@RequestMapping("/admin/substances")
public class SubstanceController {

    private final SubstanceService substanceService;

    public SubstanceController(SubstanceService substanceService) {
        this.substanceService = substanceService;
    }

    @GetMapping
    public String listSubstances(Model model) {
        model.addAttribute("substances", substanceService.getAllSubstances());
        return "substances/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("substance", new Substance());
        return "substances/create";
    }

    @PostMapping
    public String createSubstance(@ModelAttribute Substance substance) {
        substanceService.saveSubstance(substance);
        return "redirect:/admin/substances";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Substance substance = substanceService.getSubstanceById(id);
        model.addAttribute("substance", substance);
        return "substances/edit";
    }

    @PostMapping("/{id}")
    public String updateSubstance(@PathVariable Long id, @ModelAttribute Substance substance) {
        substanceService.updateSubstance(id, substance);
        return "redirect:/admin/substances";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubstance(@PathVariable Long id) {
        substanceService.deleteSubstance(id);
        return "redirect:/admin/substances";
    }

    @GetMapping("/view/{id}")
    public String viewSubstance(@PathVariable Long id, Model model) {
        model.addAttribute("substance", substanceService.getSubstanceById(id));
        return "substances/view";
    }
    
}
