package com.rgbrain.brianbot.domain.mensagens.application;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MensagemAjuda;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MensagemControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MensagemAjuda messageUseCase;

    @Test
    void dadoUmJsonDeMensagemDeUmUsuario_quandoConverterEmClasseDoDominio_deveRecuperarCamposEsperados() throws Exception {
        // GIVEN
        var jsonMessagensUpsertEntrada = """
            {
                "event": "messages.upsert",
                "instance": "BrianBotTest",
                "data": {
                    "key": {
                        "remoteJid": "554384412362@s.whatsapp.net",
                        "fromMe": true,
                        "id": "3EB0450F48856304F2D44B"
                    },
                    "pushName": "Rodrigo Gonçalves",
                    "status": "SERVER_ACK",
                    "message": {
                        "conversation": "/BrianBot dominio-parametro1-parametro2-parametro3 Olá"
                    },
                    "messageType": "conversation",
                    "messageTimestamp": 1735007705,
                    "instanceId": "e21c5134-989e-47e0-89d0-4f7934f6fb9d",
                    "source": "web"
                },
                "destination": "http://172.18.0.1:8080/brianbot",
                "date_time": "2024-12-23T23:35:05.858Z",
                "sender": "554384412362@s.whatsapp.net",
                "server_url": "http://localhost:8081",
                "apikey": "531C85A75A31-4A7E-8921-1938A03742CD"
            }
        """;

        doNothing().when(messageUseCase).postMessagesUpsert(Mockito.any(Mensagem.class));

        // WHEN
        mockMvc.perform(
            MockMvcRequestBuilders.post("/brianbot/messages-upsert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMessagensUpsertEntrada)
        )
        .andExpect(status().isOk());

        // THEN
        var argumentCaptured = ArgumentCaptor.forClass(Mensagem.class);
        verify(messageUseCase).postMessagesUpsert(argumentCaptured.capture());

        var mensagem = argumentCaptured.getValue();
        assertThat(mensagem.getEvento(), is(equalTo("messages.upsert")));
        assertThat(mensagem.getInstancia(), is(equalTo("BrianBotTest")));
        assertThat(mensagem.getIdRemoto(), is(equalTo("554384412362@s.whatsapp.net")));
        assertThat(mensagem.getEnviadoPorMim(), is(equalTo(true)));
        assertThat(mensagem.getNomeRemetente(), is(equalTo("Rodrigo Gonçalves")));
        assertThat(mensagem.getStatus(), is(equalTo("SERVER_ACK")));
        assertThat(mensagem.getMensagem(), is(equalTo("Olá")));
        assertThat(mensagem.getTipoMensagem(), is(equalTo("conversation")));
        assertThat(mensagem.getTimestampMensagem(), is(equalTo(1735007705L)));
        assertThat(mensagem.getIdInstancia(), is(equalTo("e21c5134-989e-47e0-89d0-4f7934f6fb9d")));
        assertThat(mensagem.getOrigem(), is(equalTo("web")));
        assertThat(mensagem.getIsAtivacao(), is(equalTo(true)));
        assertThat(mensagem.getComando(), is(equalTo("dominio-parametro1-parametro2-parametro3")));
        assertThat(mensagem.getComando().getDominio(), is(equalTo("dominio")));
        assertThat(mensagem.getComando().getParametros(), is(equalTo(List.of("parametro1", "parametro2", "parametro3"))));
    }
}
