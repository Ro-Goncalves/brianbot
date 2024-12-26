package com.rgbrain.brianbot.domain.mensagens.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rgbrain.brianbot.domain.mensagens.core.model.ComandoEvent;
import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MessageUseCase;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemEventPublisher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagemFacade implements MessageUseCase {

    private static final Logger logger = LoggerFactory.getLogger(MessagemFacade.class);

    //private final MensagemDataBase mensagemDataBase;
    private final MensagemEventPublisher mensagemEventPublisher;

    @Override
    public void postMessagesUpsert(Mensagem mensagem) {
        if (mensagem.getIsComando()) {
            logger.info("Comando Recebido: {}", mensagem);
            mensagemEventPublisher.publicar(new ComandoEvent(mensagem));
        }
       

        //     var resposta = "{\"number\": \"%s\",\"text\": \"Olá %s, é um prazer *INENARRAVÉL* responder a você. Sua mensagem foi: _%s_\"}".formatted(responderPara, nomeCabra, mensagemEnviada.toString());

        //     if (mensagemEnviada.toString().startsWith("/BrianBot")) {
        //         logger.info("Comando Recebido");
        //         WebClient webClient = WebClient.builder()
        //             .baseUrl("http://localhost:8081")
        //             .defaultHeader("apikey", "531C85A75A31-4A7E-8921-1938A03742CD") // Adiciona a API Key
        //             .build();
        
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
        System.err.println("Mensagem: " + mensagem);
    }
    
}