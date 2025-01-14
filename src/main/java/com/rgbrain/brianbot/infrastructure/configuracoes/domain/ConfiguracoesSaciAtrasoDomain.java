package com.rgbrain.brianbot.infrastructure.configuracoes.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rgbrain.brianbot.domain.saci.core.AtrasoFacade;
import com.rgbrain.brianbot.domain.saci.core.ports.incoming.NotificacoesAtraso;
import com.rgbrain.brianbot.domain.saci.core.ports.incoming.ObterTodosAtrasos;
import com.rgbrain.brianbot.domain.saci.core.ports.outgoing.AtrasoDataBase;
import com.rgbrain.brianbot.domain.saci.core.ports.outgoing.AtrasoEventPublisher;
import com.rgbrain.brianbot.domain.saci.infrastructure.AtrasoDataBaseAdapter;
import com.rgbrain.brianbot.domain.saci.infrastructure.AtrasoEventPublisherAdapter;
import com.rgbrain.brianbot.domain.saci.infrastructure.AtrasoRepository;

@Configuration
public class ConfiguracoesSaciAtrasoDomain {

    @Bean
    public AtrasoDataBase atrasoDataBase(AtrasoRepository atrasoRepository) {
        return new AtrasoDataBaseAdapter(atrasoRepository);
    }

    @Bean
    public AtrasoEventPublisher atrasoEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new AtrasoEventPublisherAdapter(applicationEventPublisher);
    }

    @Bean
    @Qualifier("ObterTodosAtrasos")
    public ObterTodosAtrasos obterTodosAtrasos(AtrasoDataBase atrasoDataBase, AtrasoEventPublisher atrasoEventPublisher) {
        return new AtrasoFacade(atrasoDataBase, atrasoEventPublisher);
    }

    @Bean
    @Qualifier("NotificacoesAtraso")
    public NotificacoesAtraso notificacoesAtraso(AtrasoDataBase atrasoDataBase, AtrasoEventPublisher atrasoEventPublisher) {
        return new AtrasoFacade(atrasoDataBase, atrasoEventPublisher);
    }
    
}
