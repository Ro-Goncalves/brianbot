package com.rgbrain.brianbot.domain.mensagens.core;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.model.RespostaEvent;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemEventPublisher;

@ExtendWith(MockitoExtension.class)
public class MessagemFacadeTest {

    @Mock
    private MensagemEventPublisher mensagemEventPublisher;

    @InjectMocks
    private MessagemFacade facade;

    @Test
    @DisplayName("Quando o comando for sem /BrianBot, deve fazer nada")
    public void dadoUmaMensagemSemCommando_quandoLidarComComando_deveFazerNada() {
        // given
        var mensagem = 
            Mensagem
                .builder()
                .isComando(Boolean.FALSE)         
                .comando("Meu Amor Lindo Kamy")
                .build();
       
        // when
        facade.postMessagesUpsert(mensagem);

        // then
        verifyNoInteractions(mensagemEventPublisher);
    }

    @Test
    @DisplayName("Quando o comando for sem opções, deve criar mensagem de ajuda")
    public void dadoComandoSemOpcoes_quandoLidarComComando_deveCriarMensagemDeAjuda() {
        // given
        var mensagem = 
            Mensagem
                .builder()
                .instancia("Instância")
                .idRemoto("ID Remoto")
                .idMensagem("ID Mensagem")
                .nomeRemetente("Remetente")
                .isComando(Boolean.TRUE)
                .comando("/BrianBot")
                .build();

        doNothing().when(mensagemEventPublisher).publicar(Mockito.any(RespostaEvent.class));
       
        // when
        facade.postMessagesUpsert(mensagem);

        // then
        var captor = ArgumentCaptor.forClass(RespostaEvent.class);
        verify(mensagemEventPublisher).publicar(captor.capture());
        verifyNoInteractions(mensagemEventPublisher);

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
                .isComando(Boolean.TRUE)
                .comando("/BrianBot")
                .build();
        doNothing().when(mensagemEventPublisher).publicar(Mockito.any(RespostaEvent.class));

        // when
        facade.postMessagesUpsert(mensagem);

        // then
        var captor = ArgumentCaptor.forClass(RespostaEvent.class);
        verify(mensagemEventPublisher).publicar(captor.capture());
        verifyNoInteractions(mensagemEventPublisher);
        
        var respostaEvent = captor.getValue();
        
        assertThat(respostaEvent.mensagem(), containsString("Clima"));
        assertThat(respostaEvent.mensagem(), containsString("Gramática"));
    }
}
