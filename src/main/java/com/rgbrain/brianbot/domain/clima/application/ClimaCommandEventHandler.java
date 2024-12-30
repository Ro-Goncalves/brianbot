package com.rgbrain.brianbot.domain.clima.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaAjuda;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaConsultarCidade;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaPrevisao;
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

    @Qualifier("ClimaPrevisao")
    private final ClimaPrevisao climaPrevisao;

    @EventListener
    public void handle(ComandoEvent event) {
        if (!event.dominioComando().equalsIgnoreCase("clima")) {
            logger.debug("Comando não pertence ao domínio CLIMA");
            return;
        }

        var acao = event.acaoComando();
        switch (acao) {
            case CONSULTAR:
                climaConsultarCidade.consultar(event);
                break;
            case REGISTRAR:
                climaRegistrarCidade.registrar(event);
                break;
            case PREVER:
                climaPrevisao.previsao(event);
                break;
            default:
                climaAjuda.ajudar(event);
        }
    }
}
