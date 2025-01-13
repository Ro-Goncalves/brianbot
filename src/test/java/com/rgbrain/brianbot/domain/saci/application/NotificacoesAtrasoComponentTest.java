package com.rgbrain.brianbot.domain.saci.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;

import com.rgbrain.brianbot.domain.saci.core.ports.outgoing.AtrasoDataBase;
import com.rgbrain.brianbot.domain.saci.core.ports.outgoing.AtrasoEventPublisher;
import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.RespostaEvent;

@SpringBootTest
@ActiveProfiles("test")
public class NotificacoesAtrasoComponentTest {

    @Autowired
    private AtrasoDataBase atrasoDataBase;

    @MockitoBean
    private AtrasoEventPublisher atrasoEventPublisher;

    @Autowired
    private AgendadorNotificacoesAtraso agendadorNotificacoesAtraso;

    @Test
    @DisplayName("Deve agendar a notificação de atraso")
    @Sql({"/01_insert-consorciados.sql", "/02_insert-comissionados.sql", "/03_insert-cotas.sql", "/04_insert-atrasos.sql" })
    @Sql(scripts = "/00_limpar-base.sql", executionPhase = AFTER_TEST_METHOD)
    void deveAgendarNotificacaoAtraso() {
        // Captura do evento publicado
        ArgumentCaptor<RespostaEvent> captor = ArgumentCaptor.forClass(RespostaEvent.class);

        // Executar o método agendado
        agendadorNotificacoesAtraso.notificarDetalhesAtraso();

        // Verificar se o evento foi publicado
        verify(atrasoEventPublisher, times(10)).publicarRespostaEvent(captor.capture());

        // Validar o conteúdo dos eventos
        var eventos = captor.getAllValues();
        assertEquals(10, eventos.size());

        // RespostaEvent evento1 = eventos.get(0);
        // RespostaEvent evento2 = eventos.get(1);

        // assertEquals("whatsapp1", evento1.getDestino());
        // assertTrue(evento1.getMensagem().contains("Consorciado 1"));

        // assertEquals("whatsapp2", evento2.getDestino());
        // assertTrue(evento2.getMensagem().contains("Consorciado 2"));
    }
}
