package com.rgbrain.brianbot.domain.saci.core.model.dados;

import java.util.List;

import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;

public record DadosDetalheAtraso(
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
   
    public DadosDetalheAtraso(List<Atraso> atrasos) {
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
