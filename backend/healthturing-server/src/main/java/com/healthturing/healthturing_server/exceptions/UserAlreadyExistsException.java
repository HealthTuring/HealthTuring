package com.healthturing.healthturing_server.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Excepción temporal para controlar error de registro de usuario ya existente
 * Futura optimización
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String message) {
      super(message);
  }
}
