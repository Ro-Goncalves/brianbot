package com.rgbrain.brianbot.domain.atraso.core.ports.outgoing;

import java.util.List;

import com.rgbrain.brianbot.domain.atraso.core.model.dados.DadosDetalheAtraso;
import com.rgbrain.brianbot.domain.atraso.infrastructure.entity.Atraso;

public interface AtrasoDataBase {
    List<Atraso> obterAtrasos();
    List<AtrasoComissionado> obterComissionado();
    List<Atraso> obterAtrasosComissionado(Long idComissionado);
    DadosDetalheAtraso obterAtrasosDetalhadoComissionado(Long idComissionado);
}
