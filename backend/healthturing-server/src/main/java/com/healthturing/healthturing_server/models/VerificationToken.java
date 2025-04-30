package com.healthturing.healthturing_server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "verification_token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    @Column(nullable = false)
    private Long expireDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public VerificationToken(){
        
    }

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