package com.rgbrain.brianbot.domain.saci.core.model.dados;

import java.util.List;

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

    public DadosDetalheAtraso(List<DadosCotaAtraso> cotasAtrasoComissionado) {
        this(
            cotasAtrasoComissionado.stream().mapToInt(DadosCotaAtraso::quantidadeParcelasAtraso).sum(),
            cotasAtrasoComissionado.stream().mapToDouble(DadosCotaAtraso::valorParcela).sum(),
            cotasAtrasoComissionado.stream().mapToDouble(DadosCotaAtraso::valorCredito).sum(),
            (int) cotasAtrasoComissionado.stream().filter(dadosCotaAtraso -> dadosCotaAtraso.nomeBem().equals("Imóvel")).count(),
            (int) cotasAtrasoComissionado.stream().filter(dadosCotaAtraso -> dadosCotaAtraso.nomeBem().equals("Veículo")).count(),
            (int) cotasAtrasoComissionado.stream().filter(dadosCotaAtraso -> dadosCotaAtraso.nomeBem().equals("Serviço")).count(),
            (int) cotasAtrasoComissionado.stream().filter(dadosCotaAtraso -> dadosCotaAtraso.diaVencimanto() == 8).count(),
            (int) cotasAtrasoComissionado.stream().filter(dadosCotaAtraso -> dadosCotaAtraso.diaVencimanto() == 16).count(),
            (int) cotasAtrasoComissionado.stream().filter(dadosCotaAtraso -> dadosCotaAtraso.diaVencimanto() == 20).count()
        );
    }

}
