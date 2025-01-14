package com.rgbrain.brianbot.domain.saci.application;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

import com.rgbrain.brianbot.domain.saci.core.ports.incoming.NotificacoesAtraso;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AgendadorNotificacoesAtraso {

    @Qualifier("NotificacoesAtraso")
    private final NotificacoesAtraso notificacoesAtraso;

    @Scheduled(fixedDelay = 15 * 1000)
    public void notificarDetalhesAtraso() {
        notificacoesAtraso.notificarDetalhesAtraso();
    }
}
