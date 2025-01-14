package com.rgbrain.brianbot.domain.saci.core.model.exception;

public class SaciCriarMensagemException extends RuntimeException {
    public SaciCriarMensagemException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
