package com.rgbrain.brianbot.domain.clima.core.ports.incoming;

import com.rgbrain.brianbot.domain.mensagens.core.model.ComandoEvent;

public interface ClimaRegistrarCidade {

    void registrar(ComandoEvent event);

}
