package com.rgbrain.brianbot.domain.brian.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import com.rgbrain.brianbot.application.evolution.model.EvolutionMensagemEvent;
import com.rgbrain.brianbot.domain.brian.core.ClimaService;
import com.rgbrain.brianbot.domain.brian.core.GramaticaService;
import com.rgbrain.brianbot.domain.brian.core.model.ClimaCommand;
import com.rgbrain.brianbot.infrastructure.Dominios;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BrianEventListener {
    
    @Autowired
    private ClimaService climaService;

    @Autowired
    private GramaticaService gramaticaService;

    @EventListener
    public void executar(EvolutionMensagemEvent evolutionMensagemEvent) {
        log.info("Processando evento EvolutionMensagemEvent: {}", evolutionMensagemEvent);

        var dominio = Dominios.valueOf(evolutionMensagemEvent.dominio().toUpperCase());
        log.debug("Dominio: {}", dominio);
        switch (dominio) {
            case CLIMA:
                climaService.executar(new ClimaCommand(evolutionMensagemEvent));
                break;
            case GRAMATICA:
                gramaticaService.executar();
                break;
            default:
                log.warn("Dominio não implementado: {}", dominio);
        }
    }
    
}
