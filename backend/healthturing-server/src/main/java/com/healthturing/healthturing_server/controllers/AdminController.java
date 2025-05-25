package com.healthturing.healthturing_server.controllers;

import com.healthturing.healthturing_server.models.DoctorRegistrationRequest;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.enums.Role;
import com.healthturing.healthturing_server.repositories.DoctorRegistrationRequestRepository;
import com.healthturing.healthturing_server.repositories.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String approveDoctorRequest(@PathVariable Long id) {
        DoctorRegistrationRequest request = doctorRequestRepo.findById(id).orElse(null);
        if (request != null) {

            User user = new User(request.getEmail(), request.getName(), request.getEncodedPassword());
            user.setRole(Role.ROLE_DOC);
            user.setEnabled(true);
            userRepository.save(user);

            request.setApproved(true);
            doctorRequestRepo.save(request);
        }
        return "redirect:/doctor-requests";
    }

    @PostMapping("/doctor-requests/reject/{id}")
    public String rejectDoctorRequest(@PathVariable Long id) {
        doctorRequestRepo.deleteById(id);
        return "redirect:/doctor-requests";
    }

}
