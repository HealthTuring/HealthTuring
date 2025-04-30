package com.healthturing.healthturing_server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción temporal para controlar error de formato de los parámetros recibidos
 * Futura optimización
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidFormatException extends RuntimeException{
    public InvalidFormatException(String message) {
        super(message);
    }
    
}



