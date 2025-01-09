package com.rgbrain.brianbot.infrastructure.resposta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.rgbrain.brianbot.domain.mensagens.core.model.event.RespostaEvent;

@Component
public class RespostaEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(RespostaEventHandler.class);

    @Value("${evolution.url}")
    private static String baseUrl;

    @Value("${evolution.apikey}")
    private static String apiKey;
    
    @Value("${evolution.uri}")
    private static String uri;

    @EventListener
    public void handle(RespostaEvent event) {
        logger.debug("Evento Resposta recebido: {}", event);

        var body = "{\"number\": \"%s\",\"text\": \"%s\"}".formatted(event.enviarPara(), event.mensagem());

        WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("apikey", apiKey)
            .build();

        String response = webClient.post()
            .uri(uri)
            .header("Content-Type", "application/json")
            .bodyValue(body)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        logger.debug("Resposta: {}", response);
    }
}

    

       
        

