package com.rgbrain.brianbot.infrastructure.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rgbrain.brianbot.domain.clima.core.ClimaFacade;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaAjuda;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaConsultarCidade;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaRegistrarCidade;

@Configuration
public class ConfiguracoesClimaDomain {
    
    @Bean
    @Qualifier("ClimaAjuda")
    public ClimaAjuda climaAjuda() {
        return new ClimaFacade();
    }

    @Bean
    @Qualifier("ClimaConsultarCidade")
    public ClimaConsultarCidade climaConsultarCidade() {
        return new ClimaFacade();
    }

    @Bean
    @Qualifier("ClimaRegistrarCidade")
    public ClimaRegistrarCidade climaRegistrarCidade() {
        return new ClimaFacade();
    }

    @Bean
    @Qualifier("ClimaPrevisao")
    public ClimaFacade climaPrevisao() {
        return new ClimaFacade();
    }
}
