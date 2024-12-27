package com.rgbrain.brianbot.domain.resposta.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.domain.mensagens.core.model.RespostaEvent;

@Component
public class RespostaEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(RespostaEventHandler.class);

    @EventListener
    public void handle(RespostaEvent event) {
        logger.info("Evento Resposta recebido: {}", event);
    }

     //     var resposta = "{\"number\": \"%s\",\"text\": \"Olá %s, é um prazer *INENARRAVÉL* responder a você. Sua mensagem foi: _%s_\"}".formatted(responderPara, nomeCabra, mensagemEnviada.toString());

       
        
        //         String response = webClient.post()
        //                 .uri("/message/sendText/BrianBotTest")
        //                 .header("Content-Type", "application/json")
        //                 .bodyValue(resposta)
        //                 .retrieve()
        //                 .bodyToMono(String.class)
        //                 .block();

        //         // Verifica a resposta
        //         System.out.println(response);
        //     }
        // } else {
        //     logger.warn("No 'data' field in payload: {}", payload);
        // }
}
