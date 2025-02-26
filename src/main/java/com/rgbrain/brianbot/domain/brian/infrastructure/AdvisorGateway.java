package com.rgbrain.brianbot.domain.brian.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
import com.rgbrain.brianbot.domain.brian.infrastructure.model.exception.AdvisorClientException;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.exception.AdvisorException;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.exception.AdvisorSerializationException;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoClima;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoPrecipitacao;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoSensacaoTermica;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoTemperatura;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoUmidade;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoVento;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableRetry(proxyTargetClass = true)
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

    @Autowired
    private Environment environment;

    @Retryable(
        maxAttempts = 3,
        backoff = @Backoff(delay = 5000),
        value = {AdvisorClientException.class}
    )
    public ResponsePrevisaoClima obterPrevisaoClima() {
        try {
            var previsaoClima = obterPrevisao(urlPrevisao);
            return objectMapper.readValue(previsaoClima, ResponsePrevisaoClima.class);
        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar previsão do clima. {}", e.getMessage(), e);
            throw new AdvisorSerializationException("Erro ao processar dados da previsão do clima", e);
        }
    }

    @Retryable(
        maxAttempts = 3,
        backoff = @Backoff(delay = 5000),
        value = {AdvisorClientException.class}
    )
    public ResponsePrevisaoUmidade obterPrevisaoUmidade() {
        try {
            var previsaoUmidade = obterPrevisao(urlPrevisaoUmidade);
            return objectMapper.readValue(previsaoUmidade, ResponsePrevisaoUmidade.class);
        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar previsão de umidade. {}", e.getMessage(), e);
            throw new AdvisorSerializationException("Erro ao processar dados da previsão de umidade", e);
        }
    }

    @Retryable(
        maxAttempts = 3,
        backoff = @Backoff(delay = 5000),
        value = {AdvisorClientException.class}
    )
    public ResponsePrevisaoPrecipitacao obterPrevisaoPrecipitacao() {
        try {
            var previsaoPrecipitacao = obterPrevisao(urlPrevisaoPrecipitacao);
            return objectMapper.readValue(previsaoPrecipitacao, ResponsePrevisaoPrecipitacao.class);
        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar previsão de precipitacao. {}", e.getMessage(), e);
            throw new AdvisorSerializationException("Erro ao processar dados da previsão de precipitacao", e);
        }
        
    }

    @Retryable(
        maxAttempts = 3,
        backoff = @Backoff(delay = 5000),
        value = {AdvisorClientException.class}
    )
    public ResponsePrevisaoTemperatura obterPrevisaoTemperatura() {
        try {
            var previsaoTemperatura = obterPrevisao(urlPrevisaoTemperatura);
            return objectMapper.readValue(previsaoTemperatura, ResponsePrevisaoTemperatura.class);
        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar previsão de temperatura. {}", e.getMessage(), e);
            throw new AdvisorSerializationException("Erro ao processar dados da previsão de temperatura", e);
        }
    }

    @Retryable(
        maxAttempts = 3,
        backoff = @Backoff(delay = 5000),
        value = {AdvisorClientException.class}
    )
    public ResponsePrevisaoSensacaoTermica obterPrevisaoSensacaoTermica() {
        try {
            var previsaoSensacaoTermica = obterPrevisao(urlPrevisaoSensacaoTermica);
            return objectMapper.readValue(previsaoSensacaoTermica, ResponsePrevisaoSensacaoTermica.class);
        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar previsão de sensação térmica. {}", e.getMessage(), e);
            throw new AdvisorSerializationException("Erro ao processar dados da previsão de sensação térmica", e);
        }
    }

    @Retryable(
        maxAttempts = 3,
        backoff = @Backoff(delay = 5000),
        value = {AdvisorClientException.class}
    )
    public ResponsePrevisaoVento obterPrevisaoVento() {
        try {
            var previsaoVento = obterPrevisao(urlPrevisaoVento);
            return objectMapper.readValue(previsaoVento, ResponsePrevisaoVento.class);
        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar previsão de vento. {}", e.getMessage(), e);
            throw new AdvisorSerializationException("Erro ao processar dados da previsão de vento", e);
        }
    }

    private String obterPrevisao(String url) {
        try {            
            var advisorToken = environment.getProperty("ADVISOR_API_TOKEN");
            if (advisorToken == null || advisorToken.isEmpty()) {
                throw new AdvisorException("Token de API do Advisor não configurado");
            }

            var requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> requestEntity = new HttpEntity<>(requestHeaders);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url.formatted(advisorToken),
                    HttpMethod.GET,
                    requestEntity,
                    String.class);

            return responseEntity.getBody();

        } catch (RestClientException e) {
            log.warn("Erro ao obter a previsão. {}", e.getMessage());
            throw new AdvisorClientException("Falha na requisição ao serviço - %s".formatted(e.getMessage()), e);
        }
    }

    
}
