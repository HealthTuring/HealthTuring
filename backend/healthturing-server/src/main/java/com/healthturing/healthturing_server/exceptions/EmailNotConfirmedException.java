package com.healthturing.healthturing_server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción temporal para controlar error de email no confirmado
 * Futura optimización
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class EmailNotConfirmedException extends RuntimeException{
    public EmailNotConfirmedException(String message){
        super(message);
    }
}
