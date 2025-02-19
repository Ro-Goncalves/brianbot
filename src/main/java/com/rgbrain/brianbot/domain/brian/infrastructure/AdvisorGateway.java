package com.rgbrain.brianbot.domain.brian.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.ResponsePrevisaoClima;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.ResponsePrevisaoUmidade;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableRetry
public class AdvisorGateway {

    @Value("${advisor.url.previsao}")
    private String urlPrevisao;

    @Value("${advisor.url.previsao.umidade}")
    private String urlPrevisaoUmidade;

    @Value("${advisor.url.previsao.precipitacao}")
    private String urlPrevisaoPrecipitacao;

    @Value("${advisor.url.previsao.temperatura}")
    private String urlPrevisaoTemperatura;

    @Value("${advisor.url.previsao.sensacao.termica}")
    private String urlPrevisaoSensacaoTermica;

    @Value("${advisor.url.previsao.vento}")
    private String urlPrevisaoVento;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    
    public ResponsePrevisaoClima obterPrevisaoClima() {       
        try { 
            var previsaoClima = obterPrevisao(urlPrevisao);
            var responsePrevisaoClima = objectMapper.readValue(previsaoClima, ResponsePrevisaoClima.class);
    
            return responsePrevisaoClima;

        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar o corpo da requisição. {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public ResponsePrevisaoUmidade obterPrevisaoUmidade() {
        try { 
            var previsaoUmidade = obterPrevisao(urlPrevisaoUmidade);
            var responsePrevisaoUmidade = objectMapper.readValue(previsaoUmidade, ResponsePrevisaoUmidade.class);
    
            return responsePrevisaoUmidade;

        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar o corpo da requisição. {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5 * 1000))
    private String obterPrevisao(String url) {
        try {            
            var advisorToken = System.getenv("ADVISOR_API_TOKEN");

            var requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    
            HttpEntity<Object> requestEntity = new HttpEntity<>(requestHeaders);
    
            ResponseEntity<String> responseEntity =
                restTemplate.exchange(
                    url.formatted(advisorToken),
                    HttpMethod.GET,
                    requestEntity,
                    String.class
                );
    
            return responseEntity.getBody();
    
        } catch (RestClientException e) {
            log.error("Erro ao obter a previsão. {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }    
}
