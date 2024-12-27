package com.rgbrain.brianbot.domain.clima.core.ports.outgoing;

import com.rgbrain.brianbot.domain.clima.core.model.ClimaPrevisao;

public interface ObterPrevisao {
    ClimaPrevisao previsao(Long idCidade);
}
