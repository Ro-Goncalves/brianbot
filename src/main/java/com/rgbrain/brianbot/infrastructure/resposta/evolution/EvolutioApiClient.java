package com.rgbrain.brianbot.infrastructure.resposta.evolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.RequestEnviarTexto;
import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.ResponseEnviarTexto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EvolutioApiClient {

    private static final Logger logger = LoggerFactory.getLogger(EvolutioApiClient.class);

    @Value("${evolution.url}")
    private String baseUrl;

    @Value("${evolution.apikey}")
    private String apiKey;
    
    @Value("${evolution.estancia}")
    private String estancia;
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ResponseEnviarTexto handle(RequestEnviarTexto request, String uriEnviarMensagem) {
        logger.debug("Processando Request: {}", request);
        try {
            
            var body = objectMapper.writeValueAsString(request); 
            var urlEnvio = baseUrl + uriEnviarMensagem + estancia;

            var response = enviarRequisicao(body, urlEnvio);

            var responseMessage = objectMapper.readValue(
                response,
                ResponseEnviarTexto.class
            );
        
            logger.debug("Request processada: {}", responseMessage);

            return responseMessage;
                
        } catch (JsonProcessingException e) {
            logger.error("Erro ao converter o objeto para JSON", e);
            throw new RuntimeException("Erro ao converter o objeto para JSON", e);        
        }        
    }

    private String enviarRequisicao(String body, String urlEnvio) {
        var requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("apiKey", apiKey);
        
        HttpEntity<Object> requesEntity = new HttpEntity<>(body, requestHeaders);      

        ResponseEntity<String> responseEntity =
            restTemplate.exchange(
                urlEnvio,
                HttpMethod.POST,
                requesEntity,
                String.class
            );

        return responseEntity.getBody();
    }
}
