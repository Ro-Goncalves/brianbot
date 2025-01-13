package com.rgbrain.brianbot.domain.saci.infrastructure;

import java.util.List;

import com.rgbrain.brianbot.domain.saci.core.ports.outgoing.AtrasoDataBase;
import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtrasoDataBaseAdapter implements AtrasoDataBase {
    
    private final AtrasoRepository atrasoRepository;
    
    @Override
    public List<Atraso> obterAtrasos() {
        return atrasoRepository.findAll();
    }

    @Override
    public List<Atraso> obterAtrasosPorComissionado(Long idComissionado) {
        return atrasoRepository.findByComissionado_IdComissionado(idComissionado);
    }  
}
