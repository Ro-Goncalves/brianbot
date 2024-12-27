package com.rgbrain.brianbot.domain.mensagens.core.model;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ComandoEvent {
    private String comando;
    private String dominioComando;
    private List<String> parametrosComando;

    public ComandoEvent(Mensagem mensagem) {
        this.comando = mensagem.getComando();
        this.dominioComando = mensagem.getDominioComando();
        this.parametrosComando = mensagem.getParametrosComando();
    }

    public String getAcaoComando() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAcaoComando'");
    }
}
