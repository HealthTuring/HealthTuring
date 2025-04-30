package com.healthturing.healthturing_server.exceptions;



/**
 * Excepción temporal para controlar error de JWT no válido
 * Futura optimización
 */
public class InvalidJwtException extends RuntimeException{
    public InvalidJwtException(String message){
        super(message);
    }
}
