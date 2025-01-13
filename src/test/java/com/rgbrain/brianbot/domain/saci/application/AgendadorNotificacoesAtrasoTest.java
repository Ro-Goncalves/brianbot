package com.rgbrain.brianbot.domain.saci.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.rgbrain.brianbot.domain.saci.core.ports.incoming.NotificacoesAtraso;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class AgendadorNotificacoesAtrasoTest {

    @MockitoBean
    private NotificacoesAtraso notificacoesAtraso;

    @Autowired
    private AgendadorNotificacoesAtraso agendadorNotificacoesAtraso;

    @Test
    void deveExecutarMetodoNotificarDetalhesAtraso() {
        // Configura o Awaitility para esperar até que o método agendado seja chamado
        await().atMost(15, TimeUnit.SECONDS).untilAsserted(() -> {
            // Verifica se o método foi chamado pelo menos uma vez
            verify(notificacoesAtraso, times(1)).notificarDetalhesAtraso();
        });
    }
}
