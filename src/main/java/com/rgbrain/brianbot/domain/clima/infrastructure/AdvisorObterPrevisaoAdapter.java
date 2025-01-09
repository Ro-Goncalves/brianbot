package com.rgbrain.brianbot.domain.clima.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rgbrain.brianbot.domain.clima.core.model.ClimaPrevisao;
import com.rgbrain.brianbot.domain.clima.core.ports.outgoing.ObterPrevisao;

public class AdvisorObterPrevisaoAdapter implements ObterPrevisao {    

    private static final Logger logger = LoggerFactory.getLogger(AdvisorObterPrevisaoAdapter.class);

    @Override
    public ClimaPrevisao previsao(Long idCidade) {
        logger.info("Devo ser implementado");
        return null;
    }
    
}
