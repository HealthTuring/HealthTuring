package com.healthturing.healthturing_server.dto;

public class DoctorDTO {

    private Long doctorId;
    private String name;

    public DoctorDTO() {
    }

    public DoctorDTO(Long doctorId, String name) {
        this.doctorId = doctorId;
        this.name = name;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
