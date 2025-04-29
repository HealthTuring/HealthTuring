package com.healthturing.healthturing_server.services;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretkey;

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .claim("name", user.getName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 3600))
                .signWith(getSigningKey())
                .compact();
    }

    // ?
    public String generateTokenPassword(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("id", user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 24))
                .signWith(getSigningKey())
                .compact();
    }

    public String UserAppToken(User user, Long expiration) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("key", "ApplicationToken")
                .issuedAt(new Date())
                .expiration(new Date(expiration))
                .signWith(getSigningKey())
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build().parseSignedClaims(token)
                    .getPayload();
            return claimsResolver.apply(claims);
<<<<<<< HEAD
        } catch (Exception e) {
            // Hacer excepcion personalizada
=======
        }catch(Exception e){
            //TODO: Hacer excepcion personalizada
>>>>>>> develop
            throw new RuntimeException("hola");
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

<<<<<<< HEAD
    public int extractUserId(String token) throws Exception {
=======

    public String extractRole(String token){
        return extractClaim(token, claims -> claims.get("role", String.class));
    }


    


    public int extractUserId(String token) throws Exception{
>>>>>>> develop
        return extractClaim(token, claims -> claims.get("id", Integer.class));
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

}
