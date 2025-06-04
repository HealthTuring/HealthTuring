package com.healthturing.healthturing_server.controllers;

import com.healthturing.healthturing_server.models.DoctorRegistrationRequest;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.enums.Role;
import com.healthturing.healthturing_server.repositories.DoctorRegistrationRequestRepository;
import com.healthturing.healthturing_server.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private DoctorRegistrationRequestRepository doctorRequestRepo;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/doctor-requests")
    public String showDoctorRequests(Model model) {
        List<DoctorRegistrationRequest> requests = doctorRequestRepo.findByApprovedFalse();
        model.addAttribute("requests", requests);
        return "doctor-requests";
    }

    @PostMapping("/doctor-requests/approve/{id}")
    public String approveDoctorRequest(@PathVariable Long id) {
        Optional<DoctorRegistrationRequest> optionalRequest = doctorRequestRepo.findById(id);
        if (optionalRequest.isPresent()) {
            DoctorRegistrationRequest request = optionalRequest.get();

            // Verifica si ya fue aprobado
            if (!request.isApproved()) {
                // Evita crear usuarios duplicados
                if (!userRepository.existsByEmail(request.getEmail())) {
                    User user = new User(request.getEmail(), request.getName(), request.getEncodedPassword());
                    user.setRole(Role.ROLE_DOC);
                    user.setEnabled(true);
                    userRepository.save(user);
                }

                request.setApproved(true);
                doctorRequestRepo.save(request);
            }
        }

        return "redirect:/admin/doctor-requests";
    }

    @PostMapping("/doctor-requests/reject/{id}")
    public String rejectDoctorRequest(@PathVariable Long id) {
        doctorRequestRepo.deleteById(id);
        return "redirect:/admin/doctor-requests";
    }
}
