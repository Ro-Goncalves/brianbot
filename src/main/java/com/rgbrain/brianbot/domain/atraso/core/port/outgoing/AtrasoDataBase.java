package com.rgbrain.brianbot.domain.atraso.core.port.outgoing;

import java.util.List;

import com.rgbrain.brianbot.domain.atraso.core.model.AtrasoDetalhado;
import com.rgbrain.brianbot.domain.atraso.infrastructure.entity.Atraso;

public interface AtrasoDataBase {
    List<Atraso> obterAtrasos(Long idComissionado);
    AtrasoDetalhado obterAtrasosDetalhado(Long idComissionado);
}
