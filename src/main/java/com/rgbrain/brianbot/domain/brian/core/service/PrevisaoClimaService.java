package com.rgbrain.brianbot.domain.brian.core.service;

import org.springframework.stereotype.Service;

import com.rgbrain.brianbot.domain.brian.core.model.DadosPrevisaoClima;
import com.rgbrain.brianbot.domain.brian.infrastructure.AdvisorGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrevisaoClimaService {

	private final AdvisorGateway advisorGateway;
	private final FiltraDadoTemporalService filtraDadoTemporalService;

	public DadosPrevisaoClima obterPrevisaoFiltrada() {
		var previsaoTemperatura = filtraDadoTemporalService.filtraDadoTemporal(
				advisorGateway.obterPrevisaoTemperatura().getTemperatures());

		var previsaoUmidade = filtraDadoTemporalService.filtraDadoTemporal(
				advisorGateway.obterPrevisaoUmidade().getHumidities());

		var previsaoPrecipitacao = filtraDadoTemporalService.filtraDadoTemporal(
				advisorGateway.obterPrevisaoPrecipitacao().getPrecipitations());

		var previsaoSensacaoTermica = filtraDadoTemporalService.filtraDadoTemporal(
				advisorGateway.obterPrevisaoSensacaoTermica().getThermalSensations());

		return new DadosPrevisaoClima(
				previsaoTemperatura,
				previsaoUmidade,
				previsaoPrecipitacao,
				previsaoSensacaoTermica);
	}
}
