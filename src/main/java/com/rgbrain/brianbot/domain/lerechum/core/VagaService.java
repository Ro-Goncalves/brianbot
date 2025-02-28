package com.rgbrain.brianbot.domain.lerechum.core;

import org.springframework.stereotype.Service;

import com.rgbrain.brianbot.domain.lerechum.infrastructure.model.entity.Vaga;

@Service
public class VagaService {

    public void salvarVaga(Vaga vaga) {
        
    }

    public Vaga buscarVagaPorId(Long vagaId) {
        return Vaga.builder().publicada(Boolean.TRUE).build();
    }
    
}
