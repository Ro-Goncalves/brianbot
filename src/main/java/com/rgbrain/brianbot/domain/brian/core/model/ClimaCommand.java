package com.rgbrain.brianbot.domain.brian.core.model;

import com.rgbrain.brianbot.application.evolution.model.EvolutionMensagemEvent;

public record ClimaCommand(
    String idRemoto,
    String nomeRemetente
) {

    public ClimaCommand(EvolutionMensagemEvent evolutionMensagemEvent) {
        this(
            evolutionMensagemEvent.idRemoto(),
            evolutionMensagemEvent.nomeRemetente()
        );
    }
    
}
