package com.healthturing.healthturing_server.dto;

public class PatientDTO {

    private Long id;
    private String name;
    private Long doctorId;
    private String doctorName;

    public PatientDTO() {}

    public PatientDTO(Long id, String name, Long doctorId, String doctorName) {
        this.id = id;
        this.name = name;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    
}
