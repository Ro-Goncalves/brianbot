package com.rgbrain.brianbot.domain.mensagens.core.model;

import java.util.List;

import com.rgbrain.brianbot.domain.clima.core.model.ClimaAcoes;
import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem.Comando;

public record ComandoEvent ( 
    String comando,
    String dominioComando,
    List<String> parametrosComando,
    ClimaAcoes acaoComando
){
    public ComandoEvent(Comando comando) {
        this(
            comando.getComando(),
            comando.getDominio(),
            comando.getParametros(),
            getAcaoComando(comando)
        );
    }

    private static ClimaAcoes getAcaoComando(Comando comando) {
        try {
            return ClimaAcoes.valueOf(comando.getAcao().toUpperCase());
        } catch (Exception e) {
            return ClimaAcoes.AJUDAR;
        }
    }
}