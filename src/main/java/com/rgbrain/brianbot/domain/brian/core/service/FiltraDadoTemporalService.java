package com.rgbrain.brianbot.domain.brian.core.service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import com.rgbrain.brianbot.domain.brian.core.model.DadosTemporaisFiltrados;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponseDadoTemporal;

public class FiltraDadoTemporalService {

    private static final Integer PRIMEIRA_HORA_MANHA = Integer.valueOf(7);
    private static final Integer SEGUNDA_HORA_MANHA  = Integer.valueOf(8);
    private static final Integer PRIMEIRA_HORA_TARDE = Integer.valueOf(18);
    private static final Integer SEGUNDA_HORA_TARDE  = Integer.valueOf(19);

    public static <T extends ResponseDadoTemporal> DadosTemporaisFiltrados filtraDadoTemporal(List<T> dadosTemporais) {
        var hoje = LocalDate.now();

        var dadosTemporaisFiltrados = dadosTemporais
            .stream()           
            .filter(dadoTemporal -> dadoTemporal.getDateToLocaleDateTime().toLocalDate().isEqual(hoje))
            .filter(
                filtraPorHora(PRIMEIRA_HORA_MANHA)
                    .or(filtraPorHora(SEGUNDA_HORA_MANHA))
                    .or(filtraPorHora(PRIMEIRA_HORA_TARDE))
                    .or(filtraPorHora(SEGUNDA_HORA_TARDE)))
            .toList();
            
        return new DadosTemporaisFiltrados(dadosTemporaisFiltrados);
    }

    private static Predicate<ResponseDadoTemporal> filtraPorHora(Integer hora) {
        return dadoTemporal -> dadoTemporal.getDateToLocaleDateTime().getHour() == hora;
    }
}
