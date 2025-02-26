package com.rgbrain.brianbot.domain.brian.core.model;

public record DadosPrevisaoClima(
        DadosTemporaisFiltrados previsaoTemperatura,
        DadosTemporaisFiltrados previsaoUmidade,
        DadosTemporaisFiltrados previsaoPrecipitacao,
        DadosTemporaisFiltrados previsaoSensacaoTermica

) {
    public DadosPrevisaoClima(DadosTemporaisFiltrados previsaoTemperatura, DadosTemporaisFiltrados previsaoUmidade,
            DadosTemporaisFiltrados previsaoPrecipitacao, DadosTemporaisFiltrados previsaoSensacaoTermica) {
        this.previsaoTemperatura = previsaoTemperatura;
        this.previsaoUmidade = previsaoUmidade;
        this.previsaoPrecipitacao = previsaoPrecipitacao;
        this.previsaoSensacaoTermica = previsaoSensacaoTermica;
    }

}
