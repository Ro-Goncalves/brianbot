package com.rgbrain.brianbot.domain.brian.infrastructure.model.exception;

public class AdvisorException extends RuntimeException {
    public AdvisorException(String message) {
        super(message);
    }

    public AdvisorException(String message, Throwable cause) {
        super(message, cause);
    }
}
