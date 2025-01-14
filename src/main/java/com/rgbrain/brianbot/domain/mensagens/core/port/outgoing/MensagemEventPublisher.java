package com.rgbrain.brianbot.domain.mensagens.core.port.outgoing;

import com.rgbrain.brianbot.domain.mensagens.core.model.event.ComandoEvent;
import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.RespostaEvent;

public interface MensagemEventPublisher {
    void publicar(ComandoEvent event);
    void publicar(RespostaEvent envet);
}
