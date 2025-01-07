package com.rgbrain.brianbot.domain.clima.core.ports.incoming;

import com.rgbrain.brianbot.domain.mensagens.core.model.event.ComandoEvent;

public interface ClimaPrevisao {
    void previsao(ComandoEvent event);
}
