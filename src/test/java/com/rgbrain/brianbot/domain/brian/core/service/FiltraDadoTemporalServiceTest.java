package com.rgbrain.brianbot.domain.brian.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponseDadoTemporal;

public class FiltraDadoTemporalServiceTest {

    @Test
    @DisplayName("Deve Filtrar uma Lista de Dados Temporais e Retornar Apenas os Dados de Horários Específicos")
    void deveFiltrarHorariosEspecificos() {
        // Given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var hoje = LocalDateTime.now();
        var ontem = hoje.minusDays(1L);
        var anteOntem = hoje.minusDays(2L);

        var dadosTemporais = List.of(
            new ResponseDadoTemporal(ontem.withHour(7).withMinute(0).withSecond(0).withNano(0).format(formatter), 17),
            new ResponseDadoTemporal(ontem.withHour(8).withMinute(0).withSecond(0).withNano(0).format(formatter), 18),

            new ResponseDadoTemporal(anteOntem.withHour(18).withMinute(0).withSecond(0).withNano(0).format(formatter), 28),
            new ResponseDadoTemporal(anteOntem.withHour(19).withMinute(0).withSecond(0).withNano(0).format(formatter), 29),   

            new ResponseDadoTemporal(hoje.withHour(7).withMinute(0).withSecond(0).withNano(0).format(formatter), 7),
            new ResponseDadoTemporal(hoje.withHour(8).withMinute(0).withSecond(0).withNano(0).format(formatter), 8),
            new ResponseDadoTemporal(hoje.withHour(9).withMinute(0).withSecond(0).withNano(0).format(formatter), 9),
            new ResponseDadoTemporal(hoje.withHour(18).withMinute(0).withSecond(0).withNano(0).format(formatter), 18),
            new ResponseDadoTemporal(hoje.withHour(19).withMinute(0).withSecond(0).withNano(0).format(formatter), 19),
            new ResponseDadoTemporal(hoje.withHour(20).withMinute(0).withSecond(0).withNano(0).format(formatter), 20)
        );

        // When
        var dadosTemporaisFiltrados = FiltraDadoTemporalService.filtraDadoTemporal(dadosTemporais);

        // Then
        assertThat(dadosTemporaisFiltrados.previsaoPrimeiraHoraManha()).isEqualTo(7);
        assertThat(dadosTemporaisFiltrados.previsaoSegundaHoraManha()).isEqualTo(8);

        assertThat(dadosTemporaisFiltrados.previsaoPrimeiraHoraTarde()).isEqualTo(18);
        assertThat(dadosTemporaisFiltrados.previsaoSegundaHoraTarde()).isEqualTo(19);
    }
}
