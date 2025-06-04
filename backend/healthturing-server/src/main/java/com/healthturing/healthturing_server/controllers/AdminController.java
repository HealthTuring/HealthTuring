package com.healthturing.healthturing_server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthturing.healthturing_server.models.DoctorRegistrationRequest;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.models.PatientAssignationRequest;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.enums.Role;
import com.healthturing.healthturing_server.repositories.DoctorRegistrationRequestRepository;
import com.healthturing.healthturing_server.repositories.PatientAssignationRequestRepository;
import com.healthturing.healthturing_server.repositories.PatientRepository;
import com.healthturing.healthturing_server.repositories.UserRepository;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private DoctorRegistrationRequestRepository doctorRequestRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientAssignationRequestRepository patientRequestRepo;

    @Autowired
    private PatientRepository patientRepository;

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

    @GetMapping("/patient-requests")
    public String showPatientRequests(Model model) {
        List<PatientAssignationRequest> requests = patientRequestRepo.findByApprovedFalse();
        List<User> doctors = userRepository.findByRole(Role.ROLE_DOC);
        model.addAttribute("requests", requests);
        model.addAttribute("doctors", doctors);
        return "patient-requests";
    }

    @PostMapping("/patient-requests/approve/{id}")
    public String approvePatientRequest(@PathVariable Long id, @RequestParam("doctorId") Long doctorId) {
        PatientAssignationRequest request = patientRequestRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));

        if (!request.isApproved() && request.getPatient() != null) {
            User doctor = userRepository.findById(doctorId)
                    .filter(u -> u.getRole() == Role.ROLE_DOC)
                    .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado"));
            Patient patient = request.getPatient();

            patient.setDoctor(doctor);
            patientRepository.save(patient);

            request.setApproved(true);
            patientRequestRepo.save(request);
        }
        return "redirect:/admin/patient-requests";
    }

    @PostMapping("/patient-requests/reject/{id}")
    public String rejectPatientRequest(@PathVariable Long id) {
        PatientAssignationRequest request = patientRequestRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));
        if (request.getPatient() != null) {
            patientRepository.delete(request.getPatient());
        }
        patientRequestRepo.delete(request);
        return "redirect:/admin/patient-requests";
    }

}
