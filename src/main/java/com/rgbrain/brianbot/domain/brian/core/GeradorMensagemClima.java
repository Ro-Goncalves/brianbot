package com.rgbrain.brianbot.domain.brian.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.domain.brian.core.model.DadosPrevisaoClima;

@Component
public class GeradorMensagemClima {

    @Value("${MEU_NOME_COMPLETO}")
    private String meuNomeCompleto;

    public String gerarMensagem(String template, DadosPrevisaoClima previsao) {
        return template.formatted(
                meuNomeCompleto,
                previsao.previsaoTemperatura().previsaoPrimeiraHoraManha(),
                previsao.previsaoTemperatura().previsaoSegundaHoraManha(),
                previsao.previsaoUmidade().previsaoPrimeiraHoraManha(),
                previsao.previsaoUmidade().previsaoSegundaHoraManha(),
                previsao.previsaoPrecipitacao().previsaoPrimeiraHoraManha(),
                previsao.previsaoPrecipitacao().previsaoSegundaHoraManha(),
                previsao.previsaoSensacaoTermica().previsaoPrimeiraHoraManha(),
                previsao.previsaoSensacaoTermica().previsaoSegundaHoraManha(),
                previsao.previsaoTemperatura().previsaoPrimeiraHoraTarde(),
                previsao.previsaoTemperatura().previsaoSegundaHoraTarde(),
                previsao.previsaoUmidade().previsaoPrimeiraHoraTarde(),
                previsao.previsaoUmidade().previsaoSegundaHoraTarde(),
                previsao.previsaoPrecipitacao().previsaoPrimeiraHoraTarde(),
                previsao.previsaoPrecipitacao().previsaoSegundaHoraTarde(),
                previsao.previsaoSensacaoTermica().previsaoPrimeiraHoraTarde(),
                previsao.previsaoSensacaoTermica().previsaoSegundaHoraTarde()
        );
    }
    
}
