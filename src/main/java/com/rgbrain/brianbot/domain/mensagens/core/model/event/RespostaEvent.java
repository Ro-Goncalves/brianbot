package com.rgbrain.brianbot.domain.mensagens.core.model.event;

public record RespostaEvent(
    String instancia,
    String enviarPara,
    String mensagem,
    String idMensagem
) {}
