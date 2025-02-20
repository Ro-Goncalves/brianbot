package com.rgbrain.brianbot.domain.brian.infrastructure.model.exception;

public class AdvisorClientException extends AdvisorException {
    public AdvisorClientException(String message, Throwable cause) {
        super("Erro na comunicação com o Advisor: " + message, cause);
    }
}
