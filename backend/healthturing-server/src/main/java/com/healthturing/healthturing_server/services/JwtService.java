package com.healthturing.healthturing_server.services;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.exceptions.InvalidJwtException;
import com.healthturing.healthturing_server.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secretkey;

    /**
     * Genera un jwt con datos necesarios del usuario(id, rol, nombre)
     * 
     * @param user
     * @return
     */
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .claim("name", user.getName())
                .issuer("healthturing-server")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 3600))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Genera un jwt con datos necesarios del usuario(id)
     * 
     * @param user
     * @return
     */
    public String generateTokenPassword(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("id", user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 1800))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Genera un jwt
     * 
     * @param user
     * @param expiration
     * @return
     */
    public String UserAppToken(User user, Long expiration) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("key", "ApplicationToken")
                .issuedAt(new Date())
                .expiration(new Date(expiration))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extrae los parámetros especificados en el token y comprueba que es válido
     * 
     * @param <T>
     * @param token
     * @param claimsResolver
     * @return
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build().parseSignedClaims(token)
                    .getPayload();
            return claimsResolver.apply(claims);
        } catch (JwtException e) {
            throw new InvalidJwtException("Token inválido o expirado");
        }
    }

    /**
     * Extrae Username del token mediante Claim
     * 
     * @param token String
     * @return String
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae Role del token mediante Claim
     * 
     * @param token String
     * @return String
     */
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    /**
     * Extrae Id del token mediante Claim
     * 
     * @param token String
     * @return int
     */
    public int extractUserId(String token) throws RuntimeException {
        return extractClaim(token, claims -> claims.get("id", Integer.class));
    }

    /**
     * Coge la clave de firmado para los tokens de las variables de entorno
     * 
     * @return
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Valida el token
     * 
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        final String issuer = extractClaim(token, Claims::getIssuer);

        return (username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token) &&
                "healthturing-server".equals(issuer));
    }

    /**
     * Comprueba que el token esté expirado o no
     * 
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        Date expirationDate = extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

}
