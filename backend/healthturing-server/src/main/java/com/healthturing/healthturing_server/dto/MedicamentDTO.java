package com.healthturing.healthturing_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicamentDTO {
    private Long id;
    private String commonName;
    private String dosageForm;
    private String drugRoute;
    private String strength;
}
