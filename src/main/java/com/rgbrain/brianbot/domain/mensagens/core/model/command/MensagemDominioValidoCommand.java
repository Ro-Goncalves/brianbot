package com.rgbrain.brianbot.domain.mensagens.core.model.command;

import java.util.List;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;

public record MensagemDominioValidoCommand (
    String dominio,
    String acao,
    List<String> parametros,
    String idMensagem,
    String idRemoto
){
    
    public MensagemDominioValidoCommand(Mensagem mensagem) {
        this(
            mensagem.getComando().getDominio(),
            mensagem.getComando().getAcao(),
            mensagem.getComando().getParametros(),
            mensagem.getIdMensagem(),
            mensagem.getIdRemoto()
        );
    }
}
