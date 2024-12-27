package com.rgbrain.brianbot.domain.clima.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaConsultarCidade;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaObterPrevisao;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaRegistrarCidade;
import com.rgbrain.brianbot.domain.mensagens.core.model.ComandoEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClimaCommandEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(ClimaCommandEventHandler.class);

    @Qualifier("ClimaAjuda")
    private final ClimaAjuda climaAjuda;
    
    @Qualifier("ClimaConsultarCidade")
    private final ClimaConsultarCidade climaConsultarCidade;

    @Qualifier("ClimaRegistrarCidade")
    private final ClimaRegistrarCidade climaRegistrarCidade;

    @Qualifier("ClimaObterPrevisao")
    private final ClimaObterPrevisao climaObterPrevisao;

    @EventListener
    public void handle(final ComandoEvent event) {
        if (!event.getDominioComando().equalsIgnoreCase("clima")) {
            logger.debug("Comando não pertence ao domínio CLIMA");
            return;
        }

        switch (event.getAcaoComando()) {
            case "consultar":
                climaConsultarCidade.consultar(event);
                break;
            case "registar":
                climaRegistrarCidade.registrar(event);
                break;
            case "obterprevisao":
                climaObterPrevisao.obterPrevisao(event);
                break;
            default:
                climaAjuda.ajuda(event);
        }
    }
}
