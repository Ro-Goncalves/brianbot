package com.rgbrain.brianbot.domain.saci.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.domain.saci.core.ports.incoming.NotificacoesAtraso;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AgendadorNotificacoesAtraso {

    private static final Logger logger = LoggerFactory.getLogger(AgendadorNotificacoesAtraso.class);

    @Qualifier("NotificacoesAtraso")
    private final NotificacoesAtraso notificacoesAtraso;

    @Scheduled(fixedDelay = 60 * 1000)
    public void notificarDetalhesAtraso() {
        logger.info("Iniciando o Agendador de Notificações de Atraso");
        //notificacoesAtraso.notificarDetalhesAtraso();
        logger.info("Finalizando o Agendador de Notificações de Atraso");
    }
}
