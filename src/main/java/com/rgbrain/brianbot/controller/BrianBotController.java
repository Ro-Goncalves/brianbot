package com.rgbrain.brianbot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/brianbot")
public class BrianBotController {   

    private static final Logger logger = LoggerFactory.getLogger(BrianBotController.class);

    // @PostMapping("")
    // public void postBot(@RequestBody Object entity) {
    //     System.out.println("post " + entity);
    // }

    @PostMapping("/messages-upsert")
    public void postMessagesUpsert(@RequestBody JsonNode payload) {     
        logger.info("Nome Cabra: {}", payload);  
        if (payload.has("data")) {
            StringBuilder telefoneCabra = new StringBuilder();
            StringBuilder mensagemEnviada = new StringBuilder();

            JsonNode dataNode = payload.get("data");
            JsonNode chaves = dataNode.get("key");
            if (chaves.has("participant")) {
                telefoneCabra.append(chaves.get("participant").asText());
            } else {
                telefoneCabra.append(chaves.get("remoteJid").asText());
            }            

            JsonNode tipoMensagem = dataNode.get("messageType");
            
            if (tipoMensagem.asText().equalsIgnoreCase("conversation")) {
                mensagemEnviada.append(dataNode.get("message").get("conversation").asText());
            }

            var nomeCabra = dataNode.get("pushName").asText();
            var responderPara = chaves.get("remoteJid").asText();

            logger.info("Nome Cabra: {}", nomeCabra);
            logger.info("Telefone Cabra: {}", telefoneCabra.toString().split("@")[0]);
            logger.info("Mensagem Cabra: {}", mensagemEnviada);
            logger.info("Reponder Para: {}", responderPara);

            var resposta = "{\"number\": \"%s\",\"text\": \"Olá %s, é um prazer *INENARRAVÉL* responder a você. Sua mensagem foi: _%s_\"}".formatted(responderPara, nomeCabra, mensagemEnviada.toString());

            if (mensagemEnviada.toString().startsWith("/BrianBot")) {
                logger.info("Comando Recebido");
                WebClient webClient = WebClient.builder()
                    .baseUrl("http://localhost:8081")
                    .defaultHeader("apikey", "531C85A75A31-4A7E-8921-1938A03742CD") // Adiciona a API Key
                    .build();
        
                String response = webClient.post()
                        .uri("/message/sendText/BrianBotTest")
                        .header("Content-Type", "application/json")
                        .bodyValue(resposta)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                // Verifica a resposta
                System.out.println(response);
            }
        } else {
            logger.warn("No 'data' field in payload: {}", payload);
        }
    }

    // @PostMapping("/presence-update")
    // public void postPresenceUpdate(@RequestBody Object entity) {
    //     System.out.println("Post presence-update " + entity);
    // }   

    // @PostMapping("/chats-upsert")
    // public void postChatsUpsert(@RequestBody Object entity) {
    //     System.out.println("Post chats-upsert " + entity);
    // }
}