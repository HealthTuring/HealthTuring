package com.healthturing.healthturing_server.dto;

import java.time.LocalDate;
import java.util.List;

public class TreatmentDTO {

    private Long id;
    private String nameMedication;
    private String reason;
    private String strength;
    private String dosageForm;
    private String frequency;
    private String drugRoute;
    private String duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private String prospectUrl;
    private List<IncompabilityDTO> incompatibilities;

    public TreatmentDTO() {}

    public TreatmentDTO(Long id, String nameMedication, String reason, String strength, String dosageForm, String frequency, String drugRoute, String duration, LocalDate startDate, LocalDate endDate, String prospectUrl, List<IncompabilityDTO> incompatibilities) {
        this.id = id;
        this.nameMedication = nameMedication;
        this.reason = reason;
        this.strength = strength;
        this.dosageForm = dosageForm;
        this.frequency = frequency;
        this.drugRoute = drugRoute;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prospectUrl = prospectUrl;
        this.incompatibilities = incompatibilities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameMedication() {
        return nameMedication;
    }

    public void setNameMedication(String nameMedication) {
        this.nameMedication = nameMedication;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDrugRoute() {
        return drugRoute;
    }

    public void setDrugRoute(String drugRoute) {
        this.drugRoute = drugRoute;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getProspectUrl() {
        return prospectUrl;
    }

    public void setProspectUrl(String prospectUrl) {
        this.prospectUrl = prospectUrl;
    }
    
    public List<IncompabilityDTO> getIncompatibilities() {
        return incompatibilities;
    }

    public void setIncompatibilities(List<IncompabilityDTO> incompatibilities) {
        this.incompatibilities = incompatibilities;
    }
}

