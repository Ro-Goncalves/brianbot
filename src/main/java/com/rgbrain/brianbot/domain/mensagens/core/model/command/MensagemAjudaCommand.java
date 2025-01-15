package com.rgbrain.brianbot.domain.mensagens.core.model.command;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;

import lombok.Getter;

@Getter
public class MensagemAjudaCommand {

    private String dominio;
    private String enviarMensagemPara;
    private String nomeRemetente;

    public MensagemAjudaCommand(Mensagem mensagem) {
        this.dominio = mensagem.getComando().getDominio();
        this.enviarMensagemPara = mensagem.getIdRemoto();
        this.nomeRemetente = mensagem.getNomeRemetente();
    }

}
