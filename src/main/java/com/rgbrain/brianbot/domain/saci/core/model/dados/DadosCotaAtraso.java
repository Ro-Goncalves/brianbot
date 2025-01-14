package com.rgbrain.brianbot.domain.saci.core.model.dados;

import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;

public record DadosCotaAtraso (
    String codigoGrupo,
    String codigoCota,
    String versao,
    Double valorCredito,
    Double valorParcela,
    Double valorAtraso,
    Integer quantidadeParcelasAtraso,
    String nomeBem,
    Integer diaVencimanto
) {

    public DadosCotaAtraso(Atraso atraso) {
        this(
            atraso.getCota().getCodigoGrupo(), 
            atraso.getCota().getCodigoCota(), 
            atraso.getCota().getVersao(), 
            atraso.getCota().getValorCredito(), 
            atraso.getCota().getValorParcela(), 
            atraso.getValorAtraso(),
            atraso.getQuantidadeParcelasAtraso(),
            atraso.getCota().getNomeBem(),
            atraso.getCota().getDataVencimento().getDayOfMonth()
        );
    }

}
