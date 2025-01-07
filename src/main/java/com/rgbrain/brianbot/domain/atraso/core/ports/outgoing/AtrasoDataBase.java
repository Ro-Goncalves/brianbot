package com.rgbrain.brianbot.domain.atraso.core.ports.outgoing;

import java.util.List;

import com.rgbrain.brianbot.domain.atraso.core.model.dados.DadosDetalheAtraso;
import com.rgbrain.brianbot.domain.atraso.infrastructure.entity.Atraso;

public interface AtrasoDataBase {
    List<Atraso> obterAtrasos(Long idComissionado);
    DadosDetalheAtraso obterAtrasosDetalhado(Long idComissionado);
}
