package com.rgbrain.brianbot.domain.brian.infrastructure;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.entity.FalhaRequisicao;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.exception.AdvisorClientException;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.exception.AdvisorException;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.exception.AdvisorSerializationException;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.exception.AdvisorServerException;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoClima;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoPrecipitacao;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoSensacaoTermica;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoTemperatura;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoUmidade;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoVento;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableRetry
public class AdvisorGateway {

    // Organização de propriedades em um bloco
    private final String urlPrevisao;
    private final String urlPrevisaoUmidade;
    private final String urlPrevisaoPrecipitacao;
    private final String urlPrevisaoTemperatura;
    private final String urlPrevisaoSensacaoTermica;
    private final String urlPrevisaoVento;
    private final String advisorToken;

    // Injeção de dependências por construtor
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    // Construtor para injeção de dependências
    public AdvisorGateway(
            @Value("${advisor.url.previsao}") String urlPrevisao,
            @Value("${advisor.url.previsao.umidade}") String urlPrevisaoUmidade,
            @Value("${advisor.url.previsao.precipitacao}") String urlPrevisaoPrecipitacao,
            @Value("${advisor.url.previsao.temperatura}") String urlPrevisaoTemperatura,
            @Value("${advisor.url.previsao.sensacao.termica}") String urlPrevisaoSensacaoTermica,
            @Value("${advisor.url.previsao.vento}") String urlPrevisaoVento,
            @Value("${advisor.api.token}") String advisorToken,
            RestTemplate restTemplate,
            ObjectMapper objectMapper) {

        this.urlPrevisao = urlPrevisao;
        this.urlPrevisaoUmidade = urlPrevisaoUmidade;
        this.urlPrevisaoPrecipitacao = urlPrevisaoPrecipitacao;
        this.urlPrevisaoTemperatura = urlPrevisaoTemperatura;
        this.urlPrevisaoSensacaoTermica = urlPrevisaoSensacaoTermica;
        this.urlPrevisaoVento = urlPrevisaoVento;
        this.advisorToken = advisorToken;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public ResponsePrevisaoClima obterPrevisaoClima() {
        return executarRequestEConverter(urlPrevisao, ResponsePrevisaoClima.class, "previsão do clima");
    }

    public ResponsePrevisaoUmidade obterPrevisaoUmidade() {
        return executarRequestEConverter(urlPrevisaoUmidade, ResponsePrevisaoUmidade.class, "previsão de umidade");
    }

    public ResponsePrevisaoPrecipitacao obterPrevisaoPrecipitacao() {
        return executarRequestEConverter(urlPrevisaoPrecipitacao, ResponsePrevisaoPrecipitacao.class,
                "previsão de precipitação");
    }

    public ResponsePrevisaoTemperatura obterPrevisaoTemperatura() {
        return executarRequestEConverter(urlPrevisaoTemperatura, ResponsePrevisaoTemperatura.class,
                "previsão de temperatura");
    }
    
    public ResponsePrevisaoSensacaoTermica obterPrevisaoSensacaoTermica() {
        return executarRequestEConverter(urlPrevisaoSensacaoTermica, ResponsePrevisaoSensacaoTermica.class,
                "previsão de sensação térmica");
    }

    public ResponsePrevisaoVento obterPrevisaoVento() {
        return executarRequestEConverter(urlPrevisaoVento, ResponsePrevisaoVento.class, "previsão de vento");
    }
    
    /**
     * Método genérico para executar requests e converter respostas
     */
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5000), value = { AdvisorClientException.class })
    private <T> T executarRequestEConverter(String url, Class<T> responseType, String tipoPrevisao) {
        try {
            String responseJson = obterPrevisao(url);
            return objectMapper.readValue(responseJson, responseType);
        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar {}. {}", tipoPrevisao, e.getMessage(), e);
            throw new AdvisorSerializationException("Erro ao processar dados da " + tipoPrevisao, e);
        }
    }

    @Recover
    public void recuperarFalhaPrevisaoTemperatura(AdvisorClientException e) {
        log.error("Todas as tentativas de obter previsão de temperatura falharam. Registrando o erro e retornando contingência.", e);
        
        // Salvar falha no banco
        FalhaRequisicao falha = new FalhaRequisicao();
        falha.setTipoRequisicao("TEMPERATURA");
        falha.setUrl(urlPrevisaoTemperatura);
        falha.setMensagemErro(e.getMessage());
        falha.setStackTrace(ExceptionUtils.getStackTrace(e));
        falha.setDataHora(LocalDateTime.now());
        falhaRepository.save(falha);
        
        // Retornar dados de contingência ou lançar uma exceção mais específica
        return contingenciaParaTemperatura();
    }

    private String obterPrevisao(String url) {
        validarToken();

        try {
            var requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> requestEntity = new HttpEntity<>(requestHeaders);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url.formatted(advisorToken),
                    HttpMethod.GET,
                    requestEntity,
                    String.class);

            return responseEntity.getBody();

        } catch (HttpClientErrorException e) {
            // Erros 4xx - Erros de cliente (400-499)
            log.warn("Erro de cliente ao obter a previsão. Status: {}. Mensagem: {}",
                    e.getStatusCode(), e.getMessage());
            throw new AdvisorClientException(
                    "Falha na requisição ao serviço (erro de cliente) - %s".formatted(e.getMessage()), e);

        } catch (HttpServerErrorException e) {
            // Erros 5xx - Erros de servidor (500-599)
            log.error("Erro de servidor ao obter a previsão. Status: {}. Mensagem: {}",
                    e.getStatusCode(), e.getMessage());
            throw new AdvisorServerException(
                    "Falha na requisição ao serviço (erro de servidor) - %s".formatted(e.getMessage()), e);

        } catch (RestClientException e) {
            // Outros erros de comunicação (timeout, problemas de conexão, etc.)
            log.warn("Erro de comunicação ao obter a previsão. {}", e.getMessage());
            throw new AdvisorClientException("Falha na requisição ao serviço - %s".formatted(e.getMessage()), e);
        }
    }

    private void validarToken() {
        if (advisorToken == null || advisorToken.isEmpty()) {
            throw new AdvisorException("Token de API do Advisor não configurado");
        }
    }
}