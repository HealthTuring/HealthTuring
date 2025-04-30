package com.healthturing.healthturing_server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción temporal para controlar error de JWT no válido
 * Futura optimización
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidJwtException extends RuntimeException{
    public InvalidJwtException(String message){
        super(message);
    }
}
