package com.rgbrain.brianbot.domain.saci.core.ports.outgoing;

import com.rgbrain.brianbot.infrastructure.resposta.model.RespostaEvent;

public interface AtrasoEventPublisher {
    void publicarRespostaEvent(RespostaEvent event);
}
