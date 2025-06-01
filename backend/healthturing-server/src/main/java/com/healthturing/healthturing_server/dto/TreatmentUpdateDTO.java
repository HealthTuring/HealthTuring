package com.healthturing.healthturing_server.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TreatmentUpdateDTO {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String dosesPerPeriod;
}
