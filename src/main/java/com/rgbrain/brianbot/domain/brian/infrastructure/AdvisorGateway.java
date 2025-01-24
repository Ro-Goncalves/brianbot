package com.rgbrain.brianbot.domain.brian.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.rgbrain.brianbot.domain.brian.infrastructure.model.ResponsePrevisaoClima;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
// @EnableRetry
public class AdvisorGateway {

    @Value("${advisor.url.previsao}")
    private String urlPrevisao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    // @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5 * 1000))
    public ResponsePrevisaoClima obterPrevisaoClima() {
        log.info("Devo ser implementado");
        try {            
            var advisorToken = System.getenv("ADVISOR_API_TOKEN");

            var requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);          
    
            HttpEntity<Object> requestEntity = new HttpEntity<>(requestHeaders);
    
            ResponseEntity<String> responseEntity =
                restTemplate.exchange(
                    urlPrevisao.formatted(advisorToken),
                    HttpMethod.GET,
                    requestEntity,
                    String.class
                );
    
            var reponse = objectMapper.readValue(responseEntity.getBody(), ResponsePrevisaoClima.class);
    
            return reponse;

        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar o corpo da requisição. {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    
}
