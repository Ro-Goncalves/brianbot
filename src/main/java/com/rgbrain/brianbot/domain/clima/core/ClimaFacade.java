package com.rgbrain.brianbot.domain.clima.core;

import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaAjuda;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaConsultarCidade;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaPrevisao;
import com.rgbrain.brianbot.domain.clima.core.ports.incoming.ClimaRegistrarCidade;
import com.rgbrain.brianbot.domain.mensagens.core.model.ComandoEvent;

public class ClimaFacade implements ClimaAjuda, ClimaConsultarCidade, ClimaRegistrarCidade, ClimaPrevisao{

    @Override
    public void previsao(ComandoEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterPrevisao'");
    }

    @Override
    public void registrar(ComandoEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrar'");
    }

    @Override
    public void consultar(ComandoEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultar'");
    }

    @Override
    public void ajudar(ComandoEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ajudar'");
    }
    
}
