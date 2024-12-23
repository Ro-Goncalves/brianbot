package com.rgbrain.brianbot.domain.mensagens.core;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MessageUseCase;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MessageRepository;

public class MessagesDomainFacade implements MessageUseCase {

    private final MessageRepository messageRepository;

    public MessagesDomainFacade(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void postMessagesUpsert(Mensagem mensagem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postMessagesUpsert'");
    }
    
}