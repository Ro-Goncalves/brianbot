package com.rgbrain.brianbot.domain.brian.core.service;

import java.util.List;

import com.rgbrain.brianbot.domain.brian.infrastructure.model.DadoTemporal;

public record DadosTemporaisFiltrados (
    Integer previsaoPrimeiraHoraManha,
    Integer previsaoSegundaHoraManha,
    Integer previsaoPrimeiraHoraTarde,
    Integer previsaoSegundaHoraTarde

) {
    public <T extends DadoTemporal> DadosTemporaisFiltrados(List<T> dadosTemporais) {
        this(
            dadosTemporais.get(0).getValue(),
            dadosTemporais.get(1).getValue(),
            dadosTemporais.get(2).getValue(),
            dadosTemporais.get(3).getValue()
        );
    }
}
