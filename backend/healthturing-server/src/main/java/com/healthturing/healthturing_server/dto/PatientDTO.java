package com.healthturing.healthturing_server.dto;

public class PatientDTO {

    private Long id;
    private String name;

    public PatientDTO() {}

    public PatientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
    
}
