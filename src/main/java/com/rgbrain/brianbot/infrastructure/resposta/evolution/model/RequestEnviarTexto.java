package com.rgbrain.brianbot.infrastructure.resposta.evolution.model;

public record RequestEnviarTexto(
    String number,
    String text
) {
    public RequestEnviarTexto(EnviarTextoEvent event) {
        this(event.enviarPara(), event.mensagem());
    }
}
