package com.healthturing.healthturing_server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;
    private Long expireDate;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public VerificationToken(User user, String token, Long expiration){
        this.user = user;
        this.token = token;
        this.expireDate=expiration;
    }

    public String getToken() {
        return token;
    }

    public Long getExpireDate() {
        return expireDate;
    }

    public User getUser() {
        return user;
    }


    
    
}