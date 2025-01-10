package com.rgbrain.brianbot.domain.atraso.core.ports.incoming;

import java.util.List;

import com.rgbrain.brianbot.domain.atraso.core.model.command.AtrasoComissionadoCommand;
import com.rgbrain.brianbot.domain.atraso.core.model.dados.DadosConsorciadoAtrasos;

public interface ObterTodosAtrasos {
    List<DadosConsorciadoAtrasos> handle(AtrasoComissionadoCommand obterTodosAtrasosCommand);
}
