package com.rgbrain.brianbot.domain.mensagens.core;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem.Comando;
import com.rgbrain.brianbot.domain.mensagens.core.model.event.ComandoEvent;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemEventPublisher;
import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.EnviarTextoEvent;

@ExtendWith(MockitoExtension.class)
public class MessagemFacadeTest {

    @Mock
    private MensagemEventPublisher mensagemEventPublisher;

    @InjectMocks
    private MessagemFacade facade;

    @Test
    @DisplayName("Quando a mensagem não começar com a ATIVACAO, deve fazer nada")
    public void dadoUmaMensagemSemAtivacao_quandoLidarComComando_deveFazerNada() {
        // given
        var mensagem = 
            Mensagem
                .builder()
                .isAtivacao(Boolean.FALSE)         
                .comando(null)
                .build();
       
        // when
        //facade.postMessagesUpsert(mensagem);

        // then
        verifyNoInteractions(mensagemEventPublisher);
    }

    @Test
    @DisplayName("Quando a mensagem conter apenas a ATIVACAO, deve criar mensagem de ajuda")
    public void dadoComandoSemOpcoes_quandoLidarComComando_deveCriarMensagemDeAjuda() {
        // given
        var mensagem = 
            Mensagem
                .builder()
                .instancia("Instância")
                .idRemoto("ID Remoto")
                .idMensagem("ID Mensagem")
                .nomeRemetente("Remetente")
                .isAtivacao(Boolean.TRUE)
                .comando(null)
                .build();

        doNothing().when(mensagemEventPublisher).publicar(Mockito.any(EnviarTextoEvent.class));
       
        // when
        //facade.postMessagesUpsert(mensagem);

        // then
        var captor = ArgumentCaptor.forClass(EnviarTextoEvent.class);
        verify(mensagemEventPublisher).publicar(captor.capture());
        verifyNoMoreInteractions(mensagemEventPublisher);

        var respostaEvent = captor.getValue();

        assertThat(respostaEvent.instancia(), is(equalTo(mensagem.getInstancia())));
        assertThat(respostaEvent.enviarPara(), is(equalTo(mensagem.getIdRemoto())));
        assertThat(respostaEvent.mensagem(), containsString(mensagem.getNomeRemetente()));
        assertThat(respostaEvent.idMensagem(), is(equalTo(mensagem.getIdMensagem())));
    }

    @Test
    @DisplayName("Quando o comando for sem opções, deve listar domínios na mensagem de ajuda")
    public void dadoComandoSemOpcoes_quandoLidarComComando_deveListarDominiosNaMensagemDeAjuda() {
        // given
        var mensagem = 
            Mensagem
                .builder()
                .nomeRemetente("Remetente")
                .isAtivacao(Boolean.TRUE)
                //.comando(Mensagem.Comando.builder().comando("/BrianBot").build())
                .build();
        doNothing().when(mensagemEventPublisher).publicar(Mockito.any(EnviarTextoEvent.class));

        // when
        //facade.postMessagesUpsert(mensagem);

        // then
        var captor = ArgumentCaptor.forClass(EnviarTextoEvent.class);
        verify(mensagemEventPublisher).publicar(captor.capture());
        verifyNoMoreInteractions(mensagemEventPublisher);

        var respostaEvent = captor.getValue();
        
        assertThat(respostaEvent.mensagem(), containsString("Clima"));
        assertThat(respostaEvent.mensagem(), containsString("Gramática"));
    }

    @Test
    @DisplayName("Quando o comando for com domínio, deve publicar ComandoEvent")
    public void dadoComandoComDominio_quandoLidarComComando_devePublicarComandoEvent() {
        // Given
        var mensagem = 
            Mensagem
                .builder()
                .nomeRemetente("Remetente")
                .isAtivacao(Boolean.TRUE)
                .comando(
                    Mensagem.Comando
                        .builder()
                        //.comando("Clima")
                        .dominio("Clima")
                        .parametros(List.of("Londrina"))
                        .build())
                .build();

        doNothing().when(mensagemEventPublisher).publicar(Mockito.any(ComandoEvent.class));

        // When
        //facade.postMessagesUpsert(mensagem);

        // Then
        var captor = ArgumentCaptor.forClass(ComandoEvent.class);
        verify(mensagemEventPublisher).publicar(captor.capture());
        verifyNoMoreInteractions(mensagemEventPublisher);

        var comandoEvent = captor.getValue();
        //assertThat(comandoEvent.comando(), is(equalTo(mensagem.getComando().getComando())));
        assertThat(comandoEvent.dominioComando(), is(equalTo(mensagem.getComando().getDominio())));
        assertThat(comandoEvent.parametrosComando(), is(equalTo(mensagem.getComando().getParametros())));       
    }
}
