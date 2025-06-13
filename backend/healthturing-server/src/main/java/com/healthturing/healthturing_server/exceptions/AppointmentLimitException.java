package com.healthturing.healthturing_server.exceptions;

public class AppointmentLimitException extends RuntimeException {
    public AppointmentLimitException(String message) {
        super(message);
    }
}
