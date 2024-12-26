package com.rgbrain.brianbot.domain.mensagens.application;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MessageUseCase;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MensagemControllerTest {
    
    @Mock
    private MessageUseCase messageUseCase;
    
    @InjectMocks
    private MensagemController mensagemController;

    @Autowired
    private MockMvc mockMvc;


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
                        "conversation": "Olá"
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
            MockMvcRequestBuilders.post("/messages-upsert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMessagensUpsertEntrada)
        ).andReturn();

        // THEN
        var argumentCaptured = ArgumentCaptor.forClass(Mensagem.class);
        verify(messageUseCase).postMessagesUpsert(argumentCaptured.capture());

        var mensagem = argumentCaptured.getValue();
        assert mensagem.getEvento().equals("messages.upsert");
    }
}
