package com.healthturing.healthturing_server.mapper;

import java.util.List;

import com.healthturing.healthturing_server.dto.AppointmentDTO;
import com.healthturing.healthturing_server.models.Appointment;

public class AppointmentMapper {

    public static AppointmentDTO toDto(Appointment appointment) {
        return new AppointmentDTO(
                appointment.getId(),
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getReason(),
                appointment.getPatient().getId(),
                appointment.getPatient().getName());
    }

    public static List<AppointmentDTO> toDtoList(List<Appointment> appointments) {
        return appointments.stream()
                .map(AppointmentMapper::toDto)
                .toList();
    }

}
