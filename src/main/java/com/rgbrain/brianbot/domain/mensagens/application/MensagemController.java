package com.rgbrain.brianbot.domain.mensagens.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MessageUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/brianbot")
@RequiredArgsConstructor
public class MensagemController {

    private static final Logger logger = LoggerFactory.getLogger(MensagemController.class);

    @Qualifier("MessageUseCase")
    private final MessageUseCase messageUseCase;    

    @PostMapping("/messages-upsert")
    public void messagesUpsert(@RequestBody Mensagem payload) {       
        logger.info("PAYLOAD: {}", payload);
        messageUseCase.postMessagesUpsert(payload);
        // if (payload.has("data")) {
        //     StringBuilder telefoneCabra = new StringBuilder();
        //     StringBuilder mensagemEnviada = new StringBuilder();

        //     JsonNode dataNode = payload.get("data");
        //     JsonNode chaves = dataNode.get("key");
        //     if (chaves.has("participant")) {
        //         telefoneCabra.append(chaves.get("participant").asText());
        //     } else {
        //         telefoneCabra.append(chaves.get("remoteJid").asText());
        //     }            

        //     JsonNode tipoMensagem = dataNode.get("messageType");
            
        //     if (tipoMensagem.asText().equalsIgnoreCase("conversation")) {
        //         mensagemEnviada.append(dataNode.get("message").get("conversation").asText());
        //     }

        //     var nomeCabra = dataNode.get("pushName").asText();
        //     var responderPara = chaves.get("remoteJid").asText();

        //     logger.info("Nome Cabra: {}", nomeCabra);
        //     logger.info("Telefone Cabra: {}", telefoneCabra.toString().split("@")[0]);
        //     logger.info("Mensagem Cabra: {}", mensagemEnviada);
        //     logger.info("Reponder Para: {}", responderPara);

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
        
    }
}