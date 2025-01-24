package com.rgbrain.brianbot.application.evolution.model.exception;

public class EvolutionEnviarMensagemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EvolutionEnviarMensagemException(String message) {
        super(message);
    }

    public EvolutionEnviarMensagemException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
