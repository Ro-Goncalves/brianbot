# Refatoração

Melhorias aplicadas:

Aplicação do padrão DRY (Don't Repeat Yourself): Criei um método genérico executarRequestEConverter() que elimina a duplicação de código nos métodos de obtenção das previsões.
Injeção por construtor: Substituí a anotação @Autowired por injeção via construtor, que é mais testável e explícita sobre as dependências.
Organização de código: Agrupei as propriedades de configuração e separei as verificações em métodos auxiliares como validarToken().
Tipagem genérica: Utilizei generics para o método de conversão, tornando o código mais flexível.

Padrões de projeto aplicados:

Template Method: O padrão aplicado no método executarRequestEConverter() define o "esqueleto" da operação, permitindo que os métodos específicos preencham apenas os detalhes necessários.
Factory Method: Cada método específico de obtenção atua como uma factory para um tipo específico de resposta.
Dependency Injection: Seguindo o princípio de injeção de dependência por construtor.

Técnicas de refatoração aplicadas:

Extract Method: Extraí comportamentos comuns para métodos separados.
Parameterize Method: Tornei o método de conversão parametrizável por tipo.
Replace Temp with Query: Refatorei variáveis temporárias em consultas de método.
Move Method: Reorganizei os métodos para melhor coesão.

Estas melhorias tornam o código mais fácil de manter, testar e estender no futuro.

## Antes

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

    @Value("${advisor.api.token}")
    private String advisorToken;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;    

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

## Depois
