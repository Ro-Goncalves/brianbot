package com.rgbrain.brianbot.domain.atraso.core.model;

import java.util.List;

import com.rgbrain.brianbot.domain.atraso.infrastructure.entity.Atraso;

public record AtrasoDetalhado(
    Integer quantidadeParcelasAtraso,
    Double valorTotalParcelasAtraso,
    Double valorTotalCreditoAtraso,
    Integer quantidadeParcelasAtrasoImoveis,
    Integer quantidadeParcelasAtrasoVeiculos,
    Integer quantidadeParcelasAtrasoServicos,
    Integer quantidadeAtrasiVencimento8,
    Integer quantidadeAtrasiVencimento16,
    Integer quantidadeAtrasiVencimento20   
) {
   
    public AtrasoDetalhado(List<Atraso> atrasos) {
        this(
            atrasos
                .stream()
                .mapToInt(Atraso::getQuantidadeParcelasAtraso)
                .sum(),
            atrasos
                .stream()
                .mapToDouble(Atraso::getValorAtraso)
                .sum(),
            atrasos
                .stream()
                .mapToDouble(Atraso::getValorCredito)
                .sum(),
            (int) atrasos.stream()
                .filter(atraso -> atraso.getNomeBem().equals("Imovel"))
                .count(),
            (int) atrasos.stream()
                .filter(atraso -> atraso.getNomeBem().equals("Veiculo"))
                .count(),
            (int) atrasos.stream()
                .filter(atraso -> atraso.getNomeBem().equals("Servico"))
                .count(),
            (int) atrasos.stream()
                .filter(atraso -> atraso.getDataAtraso().getDayOfMonth() == 8)
                .count(),
            (int) atrasos.stream()
                .filter(atraso -> atraso.getDataAtraso().getDayOfMonth() == 16)
                .count(),
            (int) atrasos.stream()
                .filter(atraso -> atraso.getDataAtraso().getDayOfMonth() == 20)
                .count()
        );
    }

}
