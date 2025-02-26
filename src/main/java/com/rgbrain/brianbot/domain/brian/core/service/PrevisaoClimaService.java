package com.rgbrain.brianbot.domain.brian.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgbrain.brianbot.domain.brian.core.model.DadosPrevisaoClima;
import com.rgbrain.brianbot.domain.brian.infrastructure.AdvisorGateway;
// import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoClima;

@Service
public class PrevisaoClimaService {

    @Autowired
    private AdvisorGateway advisorGateway;

    public DadosPrevisaoClima obterPrevisaoFiltrada() {
        var previsaoTemperatura = FiltraDadoTemporalService.filtraDadoTemporal(
                advisorGateway.obterPrevisaoTemperatura().getTemperatures());

        var previsaoUmidade = FiltraDadoTemporalService.filtraDadoTemporal(
                advisorGateway.obterPrevisaoUmidade().getHumidities());

        var previsaoPrecipitacao = FiltraDadoTemporalService.filtraDadoTemporal(
                advisorGateway.obterPrevisaoPrecipitacao().getPrecipitations());

        var previsaoSensacaoTermica = FiltraDadoTemporalService.filtraDadoTemporal(
                advisorGateway.obterPrevisaoSensacaoTermica().getThermalSensations());

        return new DadosPrevisaoClima(previsaoTemperatura, previsaoUmidade, previsaoPrecipitacao,
                previsaoSensacaoTermica);
    }

//     public ResponsePrevisaoClima.DailyData obterPrevisaoAtual() {
//         return advisorGateway.obterPrevisaoClima().getData().get(0);
//     }

}
