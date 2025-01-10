package com.rgbrain.brianbot.domain.saci.core.ports.outgoing;

import java.util.List;

import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;

public interface AtrasoDataBase {
    List<Atraso> obterAtrasos();
    List<Atraso> obterAtrasosPorComissionado(Long idComissionado);
}
