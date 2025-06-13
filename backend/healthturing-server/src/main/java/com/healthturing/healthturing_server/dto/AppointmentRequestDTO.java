package com.healthturing.healthturing_server.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AppointmentRequestDTO {
    public Long patientId;
    public Long doctorId;
    public LocalDate date;
    public LocalTime startTime;
    public String reason;
}