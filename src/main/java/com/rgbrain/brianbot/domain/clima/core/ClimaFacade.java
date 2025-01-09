package com.rgbrain.brianbot.domain.clima.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaAjuda;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaConsultarCidade;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaPrevisao;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaRegistrarCidade;
import com.rgbrain.brianbot.domain.mensagens.core.model.event.ComandoEvent;

public class ClimaFacade implements ClimaAjuda, ClimaConsultarCidade, ClimaRegistrarCidade, ClimaPrevisao{

    private static final Logger logger = LoggerFactory.getLogger(ClimaFacade.class);

    @Override
    public void previsao(ComandoEvent event) {
        logger.info("Devo ser implementado");
    }

    @Override
    public void registrar(ComandoEvent event) {
        logger.info("Devo ser implementado");
    }

    @Override
    public void consultar(ComandoEvent event) {
        logger.info("Devo ser implementado");
    }

    @Override
    public void ajudar(ComandoEvent event) {
        logger.info("Devo ser implementado");
    }
    
}
