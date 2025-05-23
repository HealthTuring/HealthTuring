package com.healthturing.healthturing_server.dto;

public class IncompabilityDTO {
    
    private Long id;
    private String incompatibleMedication;
    private String incompatibleSubstance;

    public IncompabilityDTO() {}

    public IncompabilityDTO(Long id, String incompatibleMedication, String incompatibleSubstance) {
        this.id = id;
        this.incompatibleMedication = incompatibleMedication;
        this.incompatibleSubstance = incompatibleSubstance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncompatibleMedication() {
        return incompatibleMedication;
    }

    public void setIncompatibleMedication(String incompatibleMedication) {
        this.incompatibleMedication = incompatibleMedication;
    }

    public String getIncompatibleSubstance() {
        return incompatibleSubstance;
    }

    public void setIncompatibleSubstance(String incompatibleSubstance) {
        this.incompatibleSubstance = incompatibleSubstance;
    }
}
