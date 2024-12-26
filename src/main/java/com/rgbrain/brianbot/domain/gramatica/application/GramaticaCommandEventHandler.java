package com.rgbrain.brianbot.domain.gramatica.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.domain.mensagens.core.model.ComandoEvent;

@Component
public class GramaticaCommandEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(GramaticaCommandEventHandler.class);

    @EventListener
    public void handle(ComandoEvent event) {
        if (event.getDominioComando().equals("gramatica")) {
            logger.info("Comando gramatica recebido: {}", event);
        }
    }
}
