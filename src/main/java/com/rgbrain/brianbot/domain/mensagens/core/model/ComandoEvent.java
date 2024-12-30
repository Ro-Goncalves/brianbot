package com.rgbrain.brianbot.domain.mensagens.core.model;

import java.util.List;

import com.rgbrain.brianbot.domain.clima.core.model.ClimaAcoes;

public record ComandoEvent ( 
    String comando,
    String dominioComando,
    List<String> parametrosComando,
    ClimaAcoes acaoComando
){
    public ComandoEvent(Mensagem mensagem) {
        this(
            mensagem.getComando(),
            mensagem.getDominioComando(),
            mensagem.getParametrosComando(),
            getAcaoComando(mensagem)
        );
    }

    private static ClimaAcoes getAcaoComando(Mensagem mensagem) {
        try {
            return ClimaAcoes.valueOf(mensagem.getAcaoComando().toUpperCase());
        } catch (Exception e) {
            return ClimaAcoes.AJUDAR;
        }
    }
}