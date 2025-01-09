package com.rgbrain.brianbot.infrastructure.configuracoes.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rgbrain.brianbot.domain.atraso.core.AtrasoFacade;
import com.rgbrain.brianbot.domain.atraso.core.ports.incoming.ObterTodosAtrasos;
import com.rgbrain.brianbot.domain.atraso.core.ports.outgoing.AtrasoDataBase;
import com.rgbrain.brianbot.domain.atraso.infrastructure.AtrasoDataBaseAdapter;
import com.rgbrain.brianbot.domain.atraso.infrastructure.AtrasoRepository;

@Configuration
public class ConfiguracoesAtrasoDomain {

    @Bean
    public AtrasoDataBase atrasoDataBase(AtrasoRepository atrasoRepository) {
        return new AtrasoDataBaseAdapter(atrasoRepository);
    }

    @Bean
    @Qualifier("ObterTodosAtrasos")
    public ObterTodosAtrasos obterTodosAtrasos(AtrasoDataBase atrasoDataBase) {
        return new AtrasoFacade(atrasoDataBase);
    }
    
}
