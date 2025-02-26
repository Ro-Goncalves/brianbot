package com.rgbrain.brianbot.domain.brian.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.domain.brian.core.ClimaFacade;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BrianAgendadorClima {

    @Autowired
    private ClimaFacade climaService;

    @Scheduled(fixedDelay = 10 * 1000)
    public void executar() {
        log.info("Brian - Agendador de Clima executando...");
        climaService.executarAgendamentoClima();
        log.info("Brian - Agendador de Clima executado.");
    }    
}
