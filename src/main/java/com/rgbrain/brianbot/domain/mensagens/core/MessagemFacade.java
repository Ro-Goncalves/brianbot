package com.rgbrain.brianbot.domain.mensagens.core;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MessageUseCase;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemDataBase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagemFacade implements MessageUseCase {

    private final MensagemDataBase mensagemDataBase;

    @Override
    public void postMessagesUpsert(Mensagem mensagem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postMessagesUpsert'");
    }
    
}