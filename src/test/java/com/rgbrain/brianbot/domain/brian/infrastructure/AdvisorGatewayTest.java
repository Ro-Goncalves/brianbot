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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgbrain.brianbot.domain.brian.AdvisorDados;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.exception.AdvisorClientException;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.exception.AdvisorException;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.exception.AdvisorSerializationException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;

@ExtendWith(MockitoExtension.class)
public class AdvisorGatewayTest {

	@Mock
	private RestTemplate restTemplate;

	@Spy
	private ObjectMapper objectMapper;

	@InjectMocks
	private AdvisorGateway advisorGateway;

	@Mock
	private Environment environment;

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(advisorGateway, "urlPrevisao", "http://api.advisor.com/previsao?token=%s");
		ReflectionTestUtils.setField(advisorGateway, "urlPrevisaoUmidade",
				"http://api.advisor.com/umidade?token=%s");
		ReflectionTestUtils.setField(advisorGateway, "urlPrevisaoPrecipitacao",
				"http://api.advisor.com/precipitacao?token=%s");

		this.objectMapper = new ObjectMapper();
		objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),
				ObjectMapper.DefaultTyping.NON_FINAL);

		when(environment.getProperty("ADVISOR_API_TOKEN")).thenReturn("token-mock");
	}

	@Test
	@DisplayName("Deve validar se os headers estão corretos")
	void deveValidarSeHeadersEstaoCorretos() {
		// Given
		var responseBody = AdvisorDados.exemploResponsePrevisaoClima();
		;
		var responseEntity = new ResponseEntity<>(responseBody, HttpStatusCode.valueOf(HttpStatus.SC_OK));
		var headerCaptor = ArgumentCaptor.forClass(HttpEntity.class);

		when(restTemplate.exchange(
				anyString(),
				eq(HttpMethod.GET),
				headerCaptor.capture(),
				eq(String.class))).thenReturn(responseEntity);

		// When
		advisorGateway.obterPrevisaoClima();

		// Then
		HttpEntity<?> capturedEntity = headerCaptor.getValue();
		HttpHeaders headers = capturedEntity.getHeaders();

		assertThat(headers.getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
	}

	@Test
	@DisplayName("Quando token não está configurado, deve lançar AdvisorException")
	void deveLancarExcecaoQuandoTokenNaoEstaConfigurado() {
		// Given
		when(environment.getProperty("ADVISOR_API_TOKEN")).thenReturn(null);

		// When/Then
		assertThatThrownBy(() -> advisorGateway.obterPrevisaoClima())
				.isInstanceOf(AdvisorException.class)
				.hasMessage("Token de API do Advisor não configurado");
	}

	@Test
	@DisplayName("Quando o serviço está indisponível, deve lançar AdvisorClientException")
	void deveObterPrevisaoClimaComErro() {
		// Given
		when(restTemplate.exchange(
				anyString(),
				eq(HttpMethod.GET),
				any(HttpEntity.class),
				eq(String.class))).thenThrow(RestClientException.class);

		// When/Then
		assertThatThrownBy(() -> advisorGateway.obterPrevisaoClima())
				.isInstanceOf(AdvisorClientException.class)
				.hasMessageContaining(
						"Erro na comunicação com o Advisor: Falha na requisição ao serviço");
	}

	@Test
	@DisplayName("Quando a URI Previsão Clima retorna dados válidos, eles devem ser deserializados para ResponsePrevisaoClima")
	void deveObterPrevisaoClimaComSucesso() throws JsonProcessingException {
		// Given
		var response = AdvisorDados.exemploResponsePrevisaoClima();

		var responseEntity = new ResponseEntity<String>(response,
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

		var data = result.getData().get(0);
		assertThat(data.getDate()).isEqualTo("2025-02-18");
		assertThat(data.getDatebr()).isEqualTo("18/02/2025");
		assertThat(data.getHumidity().getMin()).isEqualTo(54);
		assertThat(data.getHumidity().getMax()).isEqualTo(85);
		assertThat(data.getRain().getPrecipitation()).isEqualTo(6);
		assertThat(data.getWind().getVelocity()).isEqualTo(6);
		assertThat(data.getWind().getDirection()).isEqualTo("NW");
		assertThat(data.getWind().getDirectionDegrees()).isEqualTo(274);
		assertThat(data.getThermalSensation().getMin()).isEqualTo(22);
		assertThat(data.getThermalSensation().getMax()).isEqualTo(35);
		assertThat(data.getTemperature().getMin()).isEqualTo(24);
		assertThat(data.getTemperature().getMax()).isEqualTo(33);
	}

	@Test
	@DisplayName("Quando a URI Previsão Clima retorna dados inválidos, deve lançar a exceção AdvisorSerializationException")
	void deveObterPrevisaoClimaComFalha() {
		// Given
		var response = "Dados inválidos";

		var responseEntity = new ResponseEntity<String>(response,
				HttpStatusCode.valueOf(HttpStatus.SC_OK));

		when(restTemplate.exchange(
				anyString(),
				eq(HttpMethod.GET),
				any(HttpEntity.class),
				eq(String.class))).thenReturn(responseEntity);

		// When/Then
		assertThatThrownBy(() -> advisorGateway.obterPrevisaoClima())
				.isInstanceOf(AdvisorSerializationException.class);
	}

	@Test
	@DisplayName("Quando a URI Previsão Umidade retorna dados válidos, eles devem ser deserializados para ResponsePrevisaoUmidade")
	void deveObterPrevisaoUmidadeComSucesso() throws JsonProcessingException {
		// Given
		var response = AdvisorDados.exemploResponsePrevisaoUmidade();

		var responseEntity = new ResponseEntity<String>(response,
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

		var humidity = result.getHumidities().get(0);
		assertThat(humidity.getDate()).isEqualTo("2025-02-19 13:00:00");
		assertThat(humidity.getValue()).isEqualTo(79);

	}

	@Test
	@DisplayName("Quando a URI Previsão Umidade retorna dados inválidos, deve lançar a exceção AdvisorSerializationException")
	void deveObterPrevisaoUmidadeComFalha() {
		// Given
		var response = "Dados inválidos";

		var responseEntity = new ResponseEntity<String>(response,
				HttpStatusCode.valueOf(HttpStatus.SC_OK));

		when(restTemplate.exchange(
				anyString(),
				eq(HttpMethod.GET),
				any(HttpEntity.class),
				eq(String.class))).thenReturn(responseEntity);

		// When/Then
		assertThatThrownBy(() -> advisorGateway.obterPrevisaoUmidade())
				.isInstanceOf(AdvisorSerializationException.class);
	}

	@Test
	@DisplayName("Quando a URI Previsão Precipitação retorna dados válidos, eles devem ser deserializados para ResponsePrevisaoPrecipitacao")
	void deveObterPrevisaoPrecipitacaoComSucesso() throws JsonProcessingException {
		// Given
		var response = AdvisorDados.exemploResponsePrevisaoPrecipitacao();

		var responseEntity = new ResponseEntity<String>(response,
				HttpStatusCode.valueOf(HttpStatus.SC_OK));

		when(restTemplate.exchange(
				anyString(),
				eq(HttpMethod.GET),
				any(HttpEntity.class),
				eq(String.class))).thenReturn(responseEntity);

		// When
		var result = advisorGateway.obterPrevisaoPrecipitacao();

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

		assertThat(result.getPrecipitations()).hasSize(5);

		var precipitation = result.getPrecipitations().get(0);
		assertThat(precipitation.getDate()).isEqualTo("2025-02-20 22:00:00");
		assertThat(precipitation.getValue()).isEqualTo(0);

	}

	@Test
	@DisplayName("Quando a URI Previsão Precipitação retorna dados inválidos, deve lançar a exceção AdvisorSerializationException")
	void deveObterPrevisaoPrecipitacaoComFalha() {
		// Given
		var response = "Dados inválidos";

		var responseEntity = new ResponseEntity<String>(response,
				HttpStatusCode.valueOf(HttpStatus.SC_OK));

		when(restTemplate.exchange(
				anyString(),
				eq(HttpMethod.GET),
				any(HttpEntity.class),
				eq(String.class))).thenReturn(responseEntity);

		// When/Then
		assertThatThrownBy(() -> advisorGateway.obterPrevisaoPrecipitacao())
				.isInstanceOf(AdvisorSerializationException.class);
	}
}