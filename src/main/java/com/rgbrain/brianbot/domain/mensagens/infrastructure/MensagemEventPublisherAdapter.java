package com.rgbrain.brianbot.domain.mensagens.infrastructure;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.domain.mensagens.core.model.event.ComandoEvent;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemEventPublisher;
import com.rgbrain.brianbot.infrastructure.resposta.model.RespostaEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MensagemEventPublisherAdapter implements MensagemEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publicar(ComandoEvent event) {
        eventPublisher.publishEvent(event);
    }

    @Override
    public void publicar(RespostaEvent envet) {
        eventPublisher.publishEvent(envet);
    }
}
