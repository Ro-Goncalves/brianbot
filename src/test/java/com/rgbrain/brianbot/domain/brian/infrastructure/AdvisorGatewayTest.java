package com.rgbrain.brianbot.domain.brian.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgbrain.brianbot.domain.brian.AdvisorDados;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;

@ExtendWith(MockitoExtension.class)
public class AdvisorGatewayTest {

    @Mock
    private RestTemplate restTemplate;

    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private AdvisorGateway advisorGateway;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(advisorGateway, "urlPrevisao", "http://api.advisor.com/previsao?token=%s");
        ReflectionTestUtils.setField(advisorGateway, "urlPrevisaoUmidade", "http://api.advisor.com/umidade?token=%s");
    }

    @Test
    @DisplayName("Quando a URI Previsão Clima retorna dados válidos, eles devem ser deserializados para ResponsePrevisaoClima")
    void deveObterPrevisaoClimaComSucesso() throws JsonProcessingException {
        // Given
        var responsePrevisaoClima = AdvisorDados.exemploResponsePrevisaoClima();

        var responseEntity = new ResponseEntity<String>(responsePrevisaoClima,
                HttpStatusCode.valueOf(HttpStatus.SC_OK));

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class))).thenReturn(responseEntity);

        // When
        var result = advisorGateway.obterPrevisaoClima();

        // Then
        verify(restTemplate, times(1)).exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class));

        assertThat(result.getId()).isEqualTo(6997);
        assertThat(result.getName()).isEqualTo("Londrina");
        assertThat(result.getState()).isEqualTo("PR");
        assertThat(result.getCountry()).isEqualTo("BR");

        assertThat(result.getData()).hasSize(2);

        var dailyData1 = result.getData().get(0);
        assertThat(dailyData1.getDate()).isEqualTo("2025-02-18");
        assertThat(dailyData1.getDatebr()).isEqualTo("18/02/2025");
        assertThat(dailyData1.getHumidity().getMin()).isEqualTo(54);
        assertThat(dailyData1.getHumidity().getMax()).isEqualTo(85);
        assertThat(dailyData1.getRain().getPrecipitation()).isEqualTo(6);
        assertThat(dailyData1.getWind().getVelocity()).isEqualTo(6);
        assertThat(dailyData1.getWind().getDirection()).isEqualTo("NW");
        assertThat(dailyData1.getWind().getDirectionDegrees()).isEqualTo(274);
        assertThat(dailyData1.getThermalSensation().getMin()).isEqualTo(22);
        assertThat(dailyData1.getThermalSensation().getMax()).isEqualTo(35);
        assertThat(dailyData1.getTemperature().getMin()).isEqualTo(24);
        assertThat(dailyData1.getTemperature().getMax()).isEqualTo(33);
    }

    @Test
    @DisplayName("Quando a API retorna dados inválidos, deve lançar uma exceção")
    void deveObterPrevisaoClimaComFalha() {
        // Given
        var responsePrevisao = "Dados inválidos";

        var responseEntity = new ResponseEntity<String>(responsePrevisao,
                HttpStatusCode.valueOf(HttpStatus.SC_OK));

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class))).thenReturn(responseEntity);

        // When
        // Then
        assertThatThrownBy(() -> advisorGateway.obterPrevisaoClima()).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Quando o serviço está indisponível, deve lançar uma exceção")
    void deveObterPrevisaoClimaComErro() {
        // Given
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class))).thenThrow(RestClientException.class);

        // When
        // Then
        assertThatThrownBy(() -> advisorGateway.obterPrevisaoClima()).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Quando a URI Previsão Umidade retorna dados válidos, eles devem ser deserializados para ResponsePrevisaoUmidade")
    void deveObterPrevisaoUmidadeComSucesso() throws JsonProcessingException {
        // Given
        var responsePrevisaoUmidade = AdvisorDados.exemploResponsePrevisaoUmidade();

        var responseEntity = new ResponseEntity<String>(responsePrevisaoUmidade,
                HttpStatusCode.valueOf(HttpStatus.SC_OK));

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class))).thenReturn(responseEntity);

        // When
        var result = advisorGateway.obterPrevisaoUmidade();

        // Then
        verify(restTemplate, times(1)).exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class));

        assertThat(result.getId()).isEqualTo(6997);
        assertThat(result.getName()).isEqualTo("Londrina");
        assertThat(result.getState()).isEqualTo("PR");
        assertThat(result.getCountry()).isEqualTo("BR");

        assertThat(result.getHumidities()).hasSize(7);

        var dailyData1 = result.getHumidities().get(0);
        assertThat(dailyData1.getDate()).isEqualTo("2025-02-19 13:00:00");
        assertThat(dailyData1.getValue()).isEqualTo(79);
        
    }

    // @Test
    // @DisplayName("Quando a API retorna dados inválidos, deve lançar uma exceção")
    // void deveObterPrevisaoClimaComFalha() {
    //     // Given
    //     var responsePrevisao = "Dados inválidos";

    //     var responseEntity = new ResponseEntity<String>(responsePrevisao,
    //             HttpStatusCode.valueOf(HttpStatus.SC_OK));

    //     when(restTemplate.exchange(
    //             anyString(),
    //             eq(HttpMethod.GET),
    //             any(HttpEntity.class),
    //             eq(String.class))).thenReturn(responseEntity);

    //     // When
    //     // Then
    //     assertThatThrownBy(() -> advisorGateway.obterPrevisaoClima()).isInstanceOf(RuntimeException.class);
    // }

    // @Test
    // @DisplayName("Quando o serviço está indisponível, deve lançar uma exceção")
    // void deveObterPrevisaoClimaComErro() {
    //     // Given
    //     when(restTemplate.exchange(
    //             anyString(),
    //             eq(HttpMethod.GET),
    //             any(HttpEntity.class),
    //             eq(String.class))).thenThrow(RestClientException.class);

    //     // When
    //     // Then
    //     assertThatThrownBy(() -> advisorGateway.obterPrevisaoClima()).isInstanceOf(RuntimeException.class);
    // }
}

/*
 * CENÁRIOS PARA TESTES E REFATORAÇÃO

 * Testes para obterPrevisaoUmidade():
 * Mesmos cenários do método anterior, mas para o endpoint de umidade
 * 
 * Testes para obterPrevisao() (método privado):
 * Sucesso na primeira tentativa
 * Sucesso após retry (simular falha nas primeiras tentativas)
 * Falha após todas as tentativas de retry
 * Erro quando token não está disponível
 * Validar se os headers estão corretos
 * 
 * 
 */
