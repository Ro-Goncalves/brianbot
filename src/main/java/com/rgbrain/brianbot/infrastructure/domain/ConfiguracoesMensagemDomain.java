package com.rgbrain.brianbot.infrastructure.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rgbrain.brianbot.domain.mensagens.core.MessagemFacade;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MessageUseCase;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemDataBase;
import com.rgbrain.brianbot.domain.mensagens.infrastructure.MessageRepository;
import com.rgbrain.brianbot.domain.mensagens.infrastructure.MessagemDataBaseAdapter;

@Configuration
public class ConfiguracoesMensagemDomain {

    @Bean
    public MensagemDataBase messagemDataBase(MessageRepository messageRepository) {
        return new MessagemDataBaseAdapter(messageRepository);
    }

    @Bean
    @Qualifier("MessageUseCase")
    public MessageUseCase messageUseCase(MensagemDataBase mensagemDataBase) {
        return new MessagemFacade(mensagemDataBase);
    }
    
}
