package com.rgbrain.brianbot.domain.saci.infrastructure;

import java.util.List;

import com.rgbrain.brianbot.domain.saci.core.model.dados.DadosDetalheAtraso;
import com.rgbrain.brianbot.domain.saci.core.ports.outgoing.AtrasoDataBase;
import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtrasoDataBaseAdapter implements AtrasoDataBase {
    
    private final AtrasoRepository atrasoRepository;
    
    @Override
    public List<Atraso> obterAtrasosComissionado(Long idComissionado) {
        return atrasoRepository.findByIdComissionado(idComissionado);
    }

    @Override
    public DadosDetalheAtraso obterAtrasosDetalhadoComissionado(Long idComissionado) {
        var atrasos = atrasoRepository.findByIdComissionado(idComissionado);
        var atrasoDetalhado = new DadosDetalheAtraso(atrasos);
        
        return atrasoDetalhado;
    }
    
}
