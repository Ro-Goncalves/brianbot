package com.rgbrain.brianbot.application.evolution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.application.evolution.model.EvolutionMensagemEvent;
import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.EnviarTextoEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EvolutionEventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publicar(EvolutionMensagemEvent evolutionMensagemEvent) {
        log.debug("Publicando evento de EvolutionMensagemEvent: {}", evolutionMensagemEvent);
        publisher.publishEvent(evolutionMensagemEvent);
    }

    public void publicar(EnviarTextoEvent enviarTextoEvent) {
        log.debug("Publicando evento de EnviarTextoEvent: {}", enviarTextoEvent);
        publisher.publishEvent(enviarTextoEvent);
    }
    
}
