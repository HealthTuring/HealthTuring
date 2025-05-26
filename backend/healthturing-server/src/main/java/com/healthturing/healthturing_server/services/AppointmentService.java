package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.AppointmentDTO;
import com.healthturing.healthturing_server.mapper.AppointmentMapper;
import com.healthturing.healthturing_server.models.Appointment;
import com.healthturing.healthturing_server.repositories.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return AppointmentMapper.toDtoList(appointments);
    }
    
}
