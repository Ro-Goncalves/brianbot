package com.rgbrain.brianbot.infrastructure.resposta.model;

public record RespostaEvent(
    String instancia,
    String enviarPara,
    String mensagem,
    String idMensagem
) {
    public RespostaEvent(String enviarPara, String mensagem) {
        this(null, enviarPara, mensagem, null);
    }
}
