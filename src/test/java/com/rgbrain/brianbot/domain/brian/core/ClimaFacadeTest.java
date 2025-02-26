package com.rgbrain.brianbot.domain.brian.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.test.util.ReflectionTestUtils;

import com.rgbrain.brianbot.application.evolution.EvolutionEventPublisher;
import com.rgbrain.brianbot.domain.brian.core.model.DadosPrevisaoClima;
import com.rgbrain.brianbot.domain.brian.core.model.exception.ClimaEnviarMensagemException;
import com.rgbrain.brianbot.domain.brian.core.service.PrevisaoClimaService;
import com.rgbrain.brianbot.domain.brian.infrastructure.AdvisorGateway;

// @ExtendWith(SpringExtension.class) // Para carregar o contexto do Spring
@ExtendWith(MockitoExtension.class)
public class ClimaFacadeTest {

    @Mock
	private EvolutionEventPublisher evolutionEventPublisher;

	@Mock
	private PrevisaoClimaService previsaoClimaService;

	@Mock
	private GeradorMensagemClima geradorMensagemClima;

	@Mock
	private AdvisorGateway advisorGateway;

	@Mock
	private OpenAiChatModel chatModel;

    @InjectMocks
    private ClimaFacade facade;

    @Test
    @DisplayName("Deve carregar template de mensagem corretamente quando um nome válido é passado")
    void deveCarregarTemplateMensagem_QuandoNomeValido() {
        // Given
        ReflectionTestUtils.setField(facade, "nomeTemplateMensagemClimaDiario",
				"template_existente");

        var dadoPrevisao = new DadosPrevisaoClima(null, null, null, null);
        when(previsaoClimaService.obterPrevisaoFiltrada()).thenReturn(dadoPrevisao);

        // When
        facade.executarAgendamentoClima();       

        // Then
        var templateMensagem = ArgumentCaptor.forClass(String.class);
        verify(geradorMensagemClima).gerarMensagem(templateMensagem.capture(), Mockito.any(DadosPrevisaoClima.class));
        assertThat(templateMensagem.getValue())
            .isNotNull()
            .isNotEmpty();
    }

    @Test
    @DisplayName("Deve lançar ClimaEnviarMensagemException quando nome do template for inválido")
    void deveLancarClimaEnviarMensagemException_QuandoTemplateInvalido() {
        // Given
        ReflectionTestUtils.setField(facade, "nomeTemplateMensagemClimaDiario",
				"template_inexistente");
        var dadoPrevisao = new DadosPrevisaoClima(null, null, null, null);
        when(previsaoClimaService.obterPrevisaoFiltrada()).thenReturn(dadoPrevisao);

        // When & Then
        assertThatThrownBy(() -> facade.executarAgendamentoClima())
            .isInstanceOf(ClimaEnviarMensagemException.class)
            .hasMessageContaining("Erro ao recuperar");
    }

    /*
    Testes de Unidade
1. Carregar template de mensagem
Cenário: O método carregarTemplateMensagem é chamado com um nome de template válido.
Entrada: "brian_bot_mensagem_clima_diario"
Saída esperada: Retorna o conteúdo do template como String.
Cenário: O método carregarTemplateMensagem é chamado com um nome inválido.
Entrada: "template_inexistente"
Saída esperada: Lança uma ClimaEnviarMensagemException.
2. Obter previsões filtradas
Cenário: O método obterPrevisaoFiltrada retorna um objeto PrevisaoClima preenchido corretamente.
Entrada: Respostas simuladas do advisorGateway.
Saída esperada: As previsões devem conter apenas os dados filtrados corretamente.
Cenário: advisorGateway retorna valores nulos ou vazios.
Saída esperada: O serviço lida com esse caso corretamente (exemplo: retorna valores padrão ou lança uma exceção controlada).
3. Gerar mensagem climática
Cenário: O método gerarMensagem recebe um template válido e um objeto PrevisaoClima preenchido corretamente.
Saída esperada: Retorna uma String formatada corretamente com as previsões.
Cenário: O método gerarMensagem recebe um template inválido ou nulo.
Saída esperada: Lança uma exceção apropriada ou retorna um erro de formatação.
Cenário: PrevisaoClima está incompleto (exemplo: sem dados de temperatura ou umidade).
Saída esperada: O método lida com esses casos sem falhar inesperadamente.
4. Chamar modelo de IA
Cenário: O método chatModel.call recebe um prompt formatado corretamente.
Saída esperada: Retorna uma resposta simulada válida.
Cenário: chatModel.call falha (exemplo: serviço indisponível).
Saída esperada: O método trata a falha adequadamente (exemplo: loga o erro, tenta novamente ou lança exceção controlada).
5. Publicar evento
Cenário: O evento EnviarTextoEvent é criado corretamente e publicado.
Saída esperada: O evolutionEventPublisher é chamado com o evento correto.
Testes de Integração
Esses testes verificam como os componentes interagem entre si e são recomendados em estágios mais avançados da implementação.

1. Fluxo completo da previsão do clima
Cenário: Execução da executarAgendamentoClima com todas as dependências mockadas.
Saída esperada: A função executa todas as etapas sem erros e o evento final é publicado corretamente.
2. Integração com o serviço de previsão do tempo
Cenário: previsaoClimaService chama advisorGateway e processa corretamente as respostas da API externa.
Saída esperada: A integração retorna um PrevisaoClima correto.
Cenário: A API do advisorGateway está indisponível.
Saída esperada: O sistema lida com a falha (exemplo: tenta novamente, retorna valores padrão ou lança exceção controlada).
3. Integração com o modelo de IA
Cenário: chatModel.call recebe um prompt real e gera uma resposta válida.
Saída esperada: Retorna um texto formatado corretamente.
Cenário: O modelo de IA retorna um erro ou resposta inválida.
Saída esperada: O sistema lida com o erro sem quebrar o fluxo.
4. Publicação de eventos
Cenário: evolutionEventPublisher.publicar é chamado com um EnviarTextoEvent válido.
Saída esperada: O evento é publicado corretamente.
Testes de Componente
Testes mais abrangentes que garantem o funcionamento do sistema em um ambiente controlado, sem mocks.

1. Execução completa da tarefa de previsão do clima
Cenário: O método executarAgendamentoClima é executado com todas as dependências reais e um ambiente de testes controlado.
Saída esperada: O evento final com a mensagem formatada é publicado corretamente.
2. Testes de carga e desempenho
Cenário: Simular múltiplas execuções simultâneas de executarAgendamentoClima.
Saída esperada: O sistema mantém um tempo de resposta adequado e não apresenta falhas devido a concorrência.
3. Testes de resiliência
Cenário: Simular falhas na API de previsão do tempo e no modelo de IA.
Saída esperada: O sistema se recupera sem afetar a execução geral.
     */
}
