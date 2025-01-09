package com.rgbrain.brianbot.infrastructure.configuracoes.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rgbrain.brianbot.domain.mensagens.core.MessagemFacade;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MensagemAjuda;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MensagemDominioValido;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemDataBase;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemEventPublisher;
import com.rgbrain.brianbot.domain.mensagens.infrastructure.MensagemEventPublisherAdapter;
import com.rgbrain.brianbot.domain.mensagens.infrastructure.MessageRepository;
import com.rgbrain.brianbot.domain.mensagens.infrastructure.MessagemDataBaseAdapter;

@Configuration
public class ConfiguracoesMensagemDomain {

    @Bean
    public MensagemDataBase messagemDataBase(MessageRepository messageRepository) {
        // return new MessagemDataBaseAdapter(messageRepository);
        return new MessagemDataBaseAdapter();
    }

    @Bean
    public MensagemEventPublisher mensagemEventPublisher(ApplicationEventPublisher eventPublisher) {
        return new MensagemEventPublisherAdapter(eventPublisher);
        
    }

    @Bean
    @Qualifier("MensagemAjuda")
    public MensagemAjuda mensagemAjuda(MensagemDataBase mensagemDataBase, MensagemEventPublisher mensagemEventPublisher) {
        return new MessagemFacade(mensagemEventPublisher);
    }

    @Bean
    @Qualifier("MensagemDominioValido")
    public MensagemDominioValido mensagemDominioValido(MensagemDataBase mensagemDataBase, MensagemEventPublisher mensagemEventPublisher) {
        return new MessagemFacade(mensagemEventPublisher);
    }
}
