package com.rgbrain.brianbot.domain.saci.infrastructure;

import org.springframework.context.ApplicationEventPublisher;

import com.rgbrain.brianbot.domain.saci.core.ports.outgoing.AtrasoEventPublisher;
import com.rgbrain.brianbot.infrastructure.resposta.model.RespostaEvent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtrasoEventPublisherAdapter implements AtrasoEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void publicarRespostaEvent(RespostaEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
