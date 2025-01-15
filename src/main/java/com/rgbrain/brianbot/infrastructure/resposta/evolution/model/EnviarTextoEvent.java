package com.rgbrain.brianbot.infrastructure.resposta.evolution.model;

public record EnviarTextoEvent(
    String instancia,
    String enviarPara,
    String mensagem,
    String idMensagem
) {
    public EnviarTextoEvent(String enviarPara, String mensagem) {
        this(null, enviarPara, mensagem, null);
    }
}
