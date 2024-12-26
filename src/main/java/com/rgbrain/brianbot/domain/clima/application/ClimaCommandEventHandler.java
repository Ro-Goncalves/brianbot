package com.rgbrain.brianbot.domain.clima.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.domain.mensagens.core.model.ComandoEvent;

@Component
public class ClimaCommandEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(ClimaCommandEventHandler.class);

    @EventListener
    public void handle(ComandoEvent event) {
        if (event.getDominioComando().equals("clima")) {
            logger.info("Comando clima recebido: {}", event);
        }
    }
}
