package com.rgbrain.brianbot.domain.brian.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rgbrain.brianbot.domain.brian.AdvisorDados;
import com.rgbrain.brianbot.domain.brian.infrastructure.AdvisorGateway;

@ExtendWith(MockitoExtension.class)
public class PrevisaoClimaServiceTest {

	@Mock
	private AdvisorGateway advisorGateway;

	@InjectMocks
	private PrevisaoClimaService service;

	@Test
	@DisplayName("Deve obter previsões filtradas corretamente quando dados válidos são retornados")
	void deveObterPrevisoesFiltradas_QuandoDadosValidos() {
		// Given
		when(advisorGateway.obterPrevisaoUmidade())
				.thenReturn(AdvisorDados.exemploClasseResponsePrevisaoUmidade());

		when(advisorGateway.obterPrevisaoTemperatura())
				.thenReturn(AdvisorDados.exemploClasseResponsePrevisaoTemperatura());

		when(advisorGateway.obterPrevisaoPrecipitacao())
				.thenReturn(AdvisorDados.exemploClasseResponsePrevisaoPrecipitacao());

		when(advisorGateway.obterPrevisaoSensacaoTermica())
				.thenReturn(AdvisorDados.exemploClasseResponsePrevisaoSensacaoTermica());

		// When
		var dadosPrevisaoClima = service.obterPrevisaoFiltrada();

		// Then
		assertThat(dadosPrevisaoClima).isNotNull();

		assertThat(dadosPrevisaoClima.previsaoTemperatura())
				.isNotNull();

		assertThat(dadosPrevisaoClima.previsaoUmidade())
				.isNotNull();

		assertThat(dadosPrevisaoClima.previsaoPrecipitacao())
				.isNotNull();

		assertThat(dadosPrevisaoClima.previsaoSensacaoTermica())
				.isNotNull();

	}
}
