package com.rgbrain.brianbot.domain.clima.application;

import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaAjuda;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaConsultarCidade;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaPrevisao;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaRegistrarCidade;
import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem.Comando;
import com.rgbrain.brianbot.domain.mensagens.core.model.command.MensagemDominioValidoCommand;
import com.rgbrain.brianbot.domain.mensagens.core.model.event.ComandoEvent;

@ExtendWith(MockitoExtension.class)
public class ClimaCommandEventHandlerTest {

    @Mock
    private ClimaAjuda climaAjuda;

    @Mock
    private ClimaConsultarCidade climaConsultarCidade;

    @Mock
    private ClimaRegistrarCidade climaRegistrarCidade;

    @Mock
    private ClimaPrevisao climaObterPrevisao;

    @InjectMocks
    private ClimaCommandEventHandler handler;

    @Test
    @DisplayName("Deve chamar ClimaAjuda, quando não for possível identificar a ação do comando")
    void dadoUmEventoDeComandoSemAcao_quandoLidarComComando_deveChamarClimaAjuda() {
        // given
        var comandoEvent = new ComandoEvent(
            new MensagemDominioValidoCommand(
                Mensagem
                    .builder()
                    .isAtivacao(Boolean.TRUE)
                    .comando(
                        Comando.builder().dominio("clima").build()).build()
            )
        );

        // when
        handler.handle(comandoEvent);

        // then
        verify(climaAjuda).ajudar(comandoEvent);
    }

    @Test
    @DisplayName("Deve chamar ClimaConsultarCidade, quando a ação for CONSULTAR")
    void dadoUmEventoDeComandoComAcaoConsultar_quandoLidarComComando_deveChamarClimaConsultarCidade() {
        // given
        var comandoEvent = new ComandoEvent(
            new MensagemDominioValidoCommand(
                Mensagem
                    .builder()
                    .isAtivacao(Boolean.TRUE)
                    .comando(
                        Comando.builder()
                            .dominio("clima")
                            .acao("consultar")
                            .parametros(List.of("cidade", "londrina"))
                            .build())
                    .build()
            )
        );       

        // when
        handler.handle(comandoEvent);

        // then
        verify(climaConsultarCidade).consultar(comandoEvent);
    }

    @Test
    @DisplayName("Deve chamar ClimaRegistrarCidade, quando a ação for REGISTAR")
    void dadoUmEventoDeComandoComAcaoRegistrar_quandoLidarComComando_deveChamarClimaRegistrarCidade() {
        // given
        var comandoEvent = new ComandoEvent(
            new MensagemDominioValidoCommand(
                Mensagem
                    .builder()
                    .isAtivacao(Boolean.TRUE)
                    .comando(
                        Comando.builder()
                            .dominio("clima")
                            .acao("registrar")
                            .parametros(List.of("cidade", "85946"))
                            .build())
                    .build()
            )
        );

        // when
        handler.handle(comandoEvent);

        // then
        verify(climaRegistrarCidade).registrar(comandoEvent);
    }

    @Test
    @DisplayName("Deve chamar ClimaObterPrevisao, quando a ação for PREVER")
    void dadoUmEventoDeComandoComAcaoPrever_quandoLidarComComando_deveChamarClimaObterPrevisao() {
        // given
        var comandoEvent = new ComandoEvent(
            new MensagemDominioValidoCommand(
                Mensagem
                    .builder()
                    .isAtivacao(Boolean.TRUE)
                    .comando(
                        Comando.builder()
                            .dominio("clima")
                            .acao("prever")
                            .build())
                    .build()
            )
        );      

        // when
        handler.handle(comandoEvent);

        // then
        verify(climaObterPrevisao).previsao(comandoEvent);
    }

}
