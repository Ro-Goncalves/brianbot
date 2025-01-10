package com.rgbrain.brianbot.domain.saci.core.ports.incoming;

import java.util.List;

import com.rgbrain.brianbot.domain.saci.core.model.command.AtrasoComissionadoCommand;
import com.rgbrain.brianbot.domain.saci.core.model.dados.DadosConsorciadoAtrasos;

public interface ObterTodosAtrasos {
    List<DadosConsorciadoAtrasos> handle(AtrasoComissionadoCommand obterTodosAtrasosCommand);
}
