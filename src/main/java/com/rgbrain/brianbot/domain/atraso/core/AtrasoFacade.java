package com.rgbrain.brianbot.domain.atraso.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rgbrain.brianbot.domain.atraso.core.model.command.ObterTodosAtrasosCommand;
import com.rgbrain.brianbot.domain.atraso.core.model.dados.DadosConsorciadoAtrasos;
import com.rgbrain.brianbot.domain.atraso.core.model.dados.DadosCotaAtraso;
import com.rgbrain.brianbot.domain.atraso.core.ports.incoming.ObterTodosAtrasos;
import com.rgbrain.brianbot.domain.atraso.core.ports.outgoing.AtrasoDataBase;
import com.rgbrain.brianbot.domain.atraso.infrastructure.entity.Atraso;

public class AtrasoFacade implements ObterTodosAtrasos{

    private final AtrasoDataBase atrasoDataBase;

    public AtrasoFacade(AtrasoDataBase atrasoDataBase) {
        this.atrasoDataBase = atrasoDataBase;
    }

    @Override
    public List<DadosConsorciadoAtrasos> handle(ObterTodosAtrasosCommand command) {
        var todosAtrasos = atrasoDataBase.obterAtrasos(command.idComissionado());
        Map<String, DadosConsorciadoAtrasos> dadosConsorciadoAtrasosMap = new HashMap<>();
        
        for (Atraso atraso : todosAtrasos) {
            String nomeConsorciado = atraso.getNomeConsorciado();
            var dadosAtrasos = dadosConsorciadoAtrasosMap.getOrDefault(nomeConsorciado, new DadosConsorciadoAtrasos(atraso));           

            var dadosCotaAtraso = new DadosCotaAtraso(atraso);
            dadosAtrasos.adicionarCotaAtraso(dadosCotaAtraso);

            dadosConsorciadoAtrasosMap.put(nomeConsorciado, dadosAtrasos);
        }
        
        return new ArrayList<>(dadosConsorciadoAtrasosMap.values());        
    }
    
}
