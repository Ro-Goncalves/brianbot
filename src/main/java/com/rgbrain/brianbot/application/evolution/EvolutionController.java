package com.rgbrain.brianbot.application.evolution;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.rgbrain.brianbot.application.evolution.model.EvolutionMensagemEvent;
import com.rgbrain.brianbot.application.evolution.model.exception.EvolutionEnviarMensagemException;
import com.rgbrain.brianbot.infrastructure.Dominios;
import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.EnviarTextoEvent;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/brianbot")
@Slf4j
public class EvolutionController {

    @Autowired
    private EvolutionEventPublisher evolutionEventPublisher;

    @PostMapping("/messages-upsert")
    public void messagesUpsert(@RequestBody JsonNode request) {       
        log.debug("Recebendo mensagem: {}", request);

        var evolutionMensagemEvent = new EvolutionMensagemEvent(request);

        if (!evolutionMensagemEvent.isAtivacao()) {
            log.debug("Mensagem não é comando de ativação");
            return;
        }

        if (evolutionMensagemEvent.isDominioValido()) {
            evolutionEventPublisher.publicar(evolutionMensagemEvent);
            return;
        }
        
        enviarMensagemAjuda(evolutionMensagemEvent);
        return;
    }

    private void enviarMensagemAjuda(EvolutionMensagemEvent evolutionMensagemEvent) {
        try {
            var templateMensagem = 
                new String(this.getClass()
                    .getResourceAsStream("/templates/mensagem/brian_bot_mensagem_ajuda.txt")
                    .readAllBytes());

            var mensagemResposta = templateMensagem.formatted(
                evolutionMensagemEvent.nomeRemetente(),
                Dominios.dominiosDisponiveis()
            );

            evolutionEventPublisher.publicar(new EnviarTextoEvent(evolutionMensagemEvent.idRemoto(), mensagemResposta));
        } catch (IOException e) {
            throw new EvolutionEnviarMensagemException("Erro ao enviar mensagem ajuda: %s".formatted(e.getMessage()), e);
        }
    }    
}
