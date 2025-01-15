package com.rgbrain.brianbot.domain.saci.core;

import static com.rgbrain.brianbot.domain.saci.core.CriardorMensagemWhatsApp.criarMensagemDetalheAtraso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rgbrain.brianbot.domain.saci.core.model.command.AtrasoComissionadoCommand;
import com.rgbrain.brianbot.domain.saci.core.model.dados.DadosConsorciadoAtrasos;
import com.rgbrain.brianbot.domain.saci.core.model.dados.DadosCotaAtraso;
import com.rgbrain.brianbot.domain.saci.core.model.dados.DadosDetalheAtraso;
import com.rgbrain.brianbot.domain.saci.core.ports.incoming.NotificacoesAtraso;
import com.rgbrain.brianbot.domain.saci.core.ports.incoming.ObterTodosAtrasos;
import com.rgbrain.brianbot.domain.saci.core.ports.outgoing.AtrasoDataBase;
import com.rgbrain.brianbot.domain.saci.core.ports.outgoing.AtrasoEventPublisher;
import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;
import com.rgbrain.brianbot.infrastructure.resposta.evolution.model.EnviarTextoEvent;

public class AtrasoFacade implements ObterTodosAtrasos, NotificacoesAtraso{

    private final AtrasoDataBase atrasoDataBase;
    private final AtrasoEventPublisher atrasoEventPublisher;

    public AtrasoFacade(AtrasoDataBase atrasoDataBase, AtrasoEventPublisher atrasoEventPublisher) {
        this.atrasoDataBase = atrasoDataBase;
        this.atrasoEventPublisher = atrasoEventPublisher;
    }

    @Override
    public List<DadosConsorciadoAtrasos> handle(AtrasoComissionadoCommand command) {
        var todosAtrasos = atrasoDataBase.obterAtrasosPorComissionado(command.idComissionado());
        Map<String, DadosConsorciadoAtrasos> dadosConsorciadoAtrasosMap = new HashMap<>();
        
        for (Atraso atraso : todosAtrasos) {
            String nomeConsorciado = atraso.getConsorciado().getNomeConsorciado();
            var dadosAtrasos = dadosConsorciadoAtrasosMap.getOrDefault(nomeConsorciado, new DadosConsorciadoAtrasos(atraso));           

            var dadosCotaAtraso = new DadosCotaAtraso(atraso);
            dadosAtrasos.adicionarCotaAtraso(dadosCotaAtraso);

            dadosConsorciadoAtrasosMap.put(nomeConsorciado, dadosAtrasos);
        }
        
        return new ArrayList<>(dadosConsorciadoAtrasosMap.values());        
    }

    @Override
    public void notificarDetalhesAtraso() {
        var todosAtrasos = atrasoDataBase.obterAtrasos();
        var idsComissionados = todosAtrasos.stream()
            .map(Atraso::getIdComissionado)
            .distinct()
            .toList();

        idsComissionados.forEach(idComissionado -> {
            var comissionado = todosAtrasos.stream()
                .filter(atraso -> atraso.getIdComissionado() == idComissionado)
                .findFirst()
                .map(Atraso::getComissionado)
                .get();

            var cotasAtrasoComissionado = todosAtrasos.stream()
                .filter(atraso -> atraso.getIdComissionado() == idComissionado)
                .map(atraso -> new DadosCotaAtraso(atraso))
                .toList();
            
            var detalheAtrasoComissionado = new DadosDetalheAtraso(cotasAtrasoComissionado);
            var respostaEvent = new EnviarTextoEvent(
                comissionado.getWhatsappComissionado(),
                criarMensagemDetalheAtraso(comissionado.getNomeComissionado() ,detalheAtrasoComissionado)
            );
            atrasoEventPublisher.publicarRespostaEvent(respostaEvent);
        });
        
    }
    
}
