package com.rgbrain.brianbot.domain.saci.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.rgbrain.brianbot.domain.saci.core.ports.incoming.NotificacoesAtraso;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@ActiveProfiles("test")
@EnableScheduling
class AgendadorNotificacoesAtrasoTest {

    @MockitoBean
    private NotificacoesAtraso notificacoesAtraso;
   
    @TestConfiguration
    static class TestConfig {
        @Bean
        public AgendadorNotificacoesAtraso agendador(@Qualifier("NotificacoesAtraso") NotificacoesAtraso notificacoesAtraso) {
            return new AgendadorNotificacoesAtraso(notificacoesAtraso);
        }
    }

    @Test
    void deveExecutarMetodoNotificarDetalhesAtraso() {
        // Configura o Awaitility para esperar até que o método agendado seja chamado
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {            
            verify(notificacoesAtraso, atLeast(1)).notificarDetalhesAtraso();
        });
    }
}
