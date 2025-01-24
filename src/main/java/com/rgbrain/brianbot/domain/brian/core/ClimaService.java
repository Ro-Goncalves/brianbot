package com.rgbrain.brianbot.domain.brian.core;

import java.io.IOException;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgbrain.brianbot.application.evolution.EvolutionEventPublisher;
import com.rgbrain.brianbot.domain.brian.core.model.ClimaCommand;
import com.rgbrain.brianbot.domain.brian.core.model.exception.ClimaEnviarMensagemException;
import com.rgbrain.brianbot.domain.brian.infrastructure.AdvisorGateway;
import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.EnviarTextoEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClimaService {

    @Autowired
    private EvolutionEventPublisher evolutionEventPublisher;

    @Autowired
    private AdvisorGateway advisorGateway;

    @Autowired
    private OpenAiChatModel chatModel;
   
    public void executar(ClimaCommand climaCommand) {       
      try {
        var templateMensagem = new String(this.getClass()
            .getResourceAsStream("/templates/mensagem/brian_bot_mensagem_clima.txt")
            .readAllBytes());

        var previsaoClima = advisorGateway.obterPrevisaoClima();
        var climaAtual = previsaoClima.getData().get(0);

        var prompt = templateMensagem.formatted(
            climaCommand.nomeRemetente(),
            previsaoClima.getName(),
            previsaoClima.getState(),
            climaAtual.getTemperature().getMin(),
            climaAtual.getTemperature().getMax(),
            climaAtual.getHumidity().getMin(),
            climaAtual.getHumidity().getMax(),
            climaAtual.getRain().getPrecipitation(),
            climaAtual.getWind().getVelocity(),
            climaAtual.getWind().getDirection(),
            climaAtual.getThermalSensation().getMin(),
            climaAtual.getThermalSensation().getMax()
        );

        var mensagemResposta = chatModel.call(prompt);

        evolutionEventPublisher.publicar(new EnviarTextoEvent(climaCommand.idRemoto(), mensagemResposta));
        
      } catch (IOException e) {
        throw new ClimaEnviarMensagemException("Erro ao enviar mensagem ajuda: %s".formatted(e.getMessage()), e);
      }
    }
    
}
