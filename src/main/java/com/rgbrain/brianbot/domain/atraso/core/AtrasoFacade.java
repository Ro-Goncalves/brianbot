package com.rgbrain.brianbot.domain.atraso.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rgbrain.brianbot.domain.atraso.core.model.command.AtrasoComissionadoCommand;
import com.rgbrain.brianbot.domain.atraso.core.model.dados.DadosConsorciadoAtrasos;
import com.rgbrain.brianbot.domain.atraso.core.model.dados.DadosCotaAtraso;
import com.rgbrain.brianbot.domain.atraso.core.ports.incoming.ObterDetalhesAtraso;
import com.rgbrain.brianbot.domain.atraso.core.ports.incoming.ObterTodosAtrasos;
import com.rgbrain.brianbot.domain.atraso.core.ports.outgoing.AtrasoDataBase;
import com.rgbrain.brianbot.domain.atraso.infrastructure.entity.Atraso;
import com.rgbrain.brianbot.infrastructure.resposta.model.RespostaEvent;

public class AtrasoFacade implements ObterTodosAtrasos, ObterDetalhesAtraso{

    private final AtrasoDataBase atrasoDataBase;

    public AtrasoFacade(AtrasoDataBase atrasoDataBase) {
        this.atrasoDataBase = atrasoDataBase;
    }

    @Override
    public List<DadosConsorciadoAtrasos> handle(AtrasoComissionadoCommand command) {
        var todosAtrasos = atrasoDataBase.obterAtrasosComissionado(command.idComissionado());
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

    @Override
    public void obterDetalhesAtraso() {
        var comissionados = atrasoDataBase.obterComissionado();
        //var comissionados = todosAtrasos.stream().map(Atraso::getIdComissionado).distinct().toList();

        comissionados.forEach(comissionado -> {
            var detalheAtrasoComissionado = atrasoDataBase.obterAtrasosDetalhadoComissionado(comissionado.idComissionado());
            var respostaEvent = new RespostaEvent(
                comissionado.whatsAppComissionado(),
                ""
            );
        });
        
    }
    
}
