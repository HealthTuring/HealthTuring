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

/**
 * Entidad que representa los tokens para habilitar usuarios no habilitados
 * Representada en la base de datos como tabla "verification_token"
 * Contiene String(token) Long(expirationDate) y un User enlazado obligatoriamente
 */
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

    /**
     * Constructor vacío de la entidad para manejo de entidades de Springboot
     */
    public VerificationToken(){
        
    }

    /**
     * Constructor de la entidad empleado al registrar un nuevo usuario
     * @param user
     * @param token
     * @param expiration
     */
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