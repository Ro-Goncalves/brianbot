package com.rgbrain.brianbot.domain.mensagens.core.port.outgoing;

import com.rgbrain.brianbot.domain.mensagens.core.model.ComandoEvent;

public interface MensagemEventPublisher {
    void publicar(ComandoEvent event);
}
