package com.healthturing.healthturing_server.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TreatmentCreateDTO {

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String dosesPerPeriod;
    private Long patientId;
    private Long medicamentId;
    
}
