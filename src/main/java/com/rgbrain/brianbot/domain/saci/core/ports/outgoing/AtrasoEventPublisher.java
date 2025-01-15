package com.rgbrain.brianbot.domain.saci.core.ports.outgoing;

import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.EnviarTextoEvent;

public interface AtrasoEventPublisher {
    void publicarRespostaEvent(EnviarTextoEvent event);
}
