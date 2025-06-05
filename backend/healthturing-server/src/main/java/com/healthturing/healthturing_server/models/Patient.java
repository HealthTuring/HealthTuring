package com.healthturing.healthturing_server.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.healthturing.healthturing_server.models.enums.BloodGroup;
import com.healthturing.healthturing_server.models.enums.Gender;
import com.healthturing.healthturing_server.models.enums.RhFactor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String dni;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Gender gender;

    private BloodGroup bloodGroup;

    private RhFactor rhFactor;

    @Column(length = 200)
    private String emergencyContact;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private boolean doctorAssigned = false;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Treatment> treatments = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    public Patient() {
    }

    public Patient(String name, String dni, LocalDate dateOfBirth, Gender gender, BloodGroup bloodGroup,
            RhFactor rhFactor, String emergencyContact, User user, User doctor) {
        this.name = name;
        this.dni = dni;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.rhFactor = rhFactor;
        this.emergencyContact = emergencyContact;
        this.user = user;
        this.doctor = doctor;
        this.doctorAssigned = true;
    }

}
