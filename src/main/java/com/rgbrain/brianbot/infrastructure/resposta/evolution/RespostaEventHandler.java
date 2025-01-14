package com.rgbrain.brianbot.infrastructure.resposta.evolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.RespostaEvent;

@Component
public class RespostaEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(RespostaEventHandler.class);

    @Value("${evolution.url}")
    private String baseUrl;

    @Value("${evolution.apikey}")
    private String apiKey;
    
    @Value("${evolution.uri}")
    private String uri;

    @EventListener
    public void handle(RespostaEvent event) {
        logger.debug("Evento Resposta recebido: {}", event);
       
        var body = "{\"number\": \"%s\",\"text\": \"%s\"}".formatted(event.enviarPara(), event.mensagem());
        logger.debug("body: {}", body);

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

    

       
        

