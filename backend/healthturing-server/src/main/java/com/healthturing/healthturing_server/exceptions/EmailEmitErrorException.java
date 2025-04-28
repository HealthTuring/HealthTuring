package com.healthturing.healthturing_server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EmailEmitErrorException extends RuntimeException{
    public EmailEmitErrorException(String message){
        super(message);
    }    
}
