package com.eventmanager.backend.application.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
