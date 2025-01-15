package com.rgbrain.brianbot.infrastructure.resposta.evolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.RequestEnviarTexto;
import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.EnviarTextoEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RespostaEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(RespostaEventHandler.class);

    @Value("${evolution.uri.enviar.texto}")
    private String uriEnviarTexto;

    private EvolutioApiClient evolutioApiClient;

    @EventListener
    public void handle(EnviarTextoEvent event) {
        logger.debug("Evento Resposta recebido: {}", event);
        
        var requestEnviarMensagem = new RequestEnviarTexto(event);
        var responseMessage = evolutioApiClient.handle(requestEnviarMensagem, uriEnviarTexto);
    
        logger.debug("Resposta processada: {}", responseMessage);
    }
}

    

       
        

