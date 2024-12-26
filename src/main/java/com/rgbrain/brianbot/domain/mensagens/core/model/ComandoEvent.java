package com.rgbrain.brianbot.domain.mensagens.core.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ComandoEvent {
    private String comando;
    private String dominioComando;
    private String[] parametrosComando;

    public ComandoEvent(Mensagem mensagem) {
        this.comando = mensagem.getComando();
        this.dominioComando = mensagem.getDominioComando();
        this.parametrosComando = mensagem.getParametrosComando();
    }
}
