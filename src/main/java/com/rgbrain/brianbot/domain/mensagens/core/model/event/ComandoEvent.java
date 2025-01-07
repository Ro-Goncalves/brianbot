package com.rgbrain.brianbot.domain.mensagens.core.model.event;

import java.util.List;

import com.rgbrain.brianbot.domain.clima.core.model.ClimaAcoes;
import com.rgbrain.brianbot.domain.mensagens.core.model.command.MensagemDominioValidoCommand;

public record ComandoEvent (     
    String dominioComando,
    ClimaAcoes acaoComando,
    List<String> parametrosComando,
    String idMensagem,
    String idRemoto
){
    public ComandoEvent(MensagemDominioValidoCommand command) {
        this(
            command.dominio(),
            getAcaoComando(command.acao()),
            command.parametros(),
            command.idMensagem(),
            command.idRemoto()
        );
    }

    private static ClimaAcoes getAcaoComando(String acao) {
        try {
            return ClimaAcoes.valueOf(acao.toUpperCase());
        } catch (Exception e) {
            return ClimaAcoes.AJUDAR;
        }
    }
}