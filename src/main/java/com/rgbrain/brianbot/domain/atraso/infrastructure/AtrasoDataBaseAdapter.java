package com.rgbrain.brianbot.domain.atraso.infrastructure;

import java.util.List;

import com.rgbrain.brianbot.domain.atraso.core.model.dados.DadosDetalheAtraso;
import com.rgbrain.brianbot.domain.atraso.core.ports.outgoing.AtrasoDataBase;
import com.rgbrain.brianbot.domain.atraso.infrastructure.entity.Atraso;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtrasoDataBaseAdapter implements AtrasoDataBase {
    
    private final AtrasoRepository atrasoRepository;
    
    @Override
    public List<Atraso> obterAtrasos(Long idComissionado) {
        return atrasoRepository.findByIdComissionado(idComissionado);
    }

    @Override
    public DadosDetalheAtraso obterAtrasosDetalhado(Long idComissionado) {
        var atrasos = atrasoRepository.findByIdComissionado(idComissionado);
        var atrasoDetalhado = new DadosDetalheAtraso(atrasos);
        
        return atrasoDetalhado;
    }
    
}
