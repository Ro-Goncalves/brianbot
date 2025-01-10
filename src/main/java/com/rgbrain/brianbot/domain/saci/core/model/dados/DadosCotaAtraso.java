package com.rgbrain.brianbot.domain.saci.core.model.dados;

import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;

public record DadosCotaAtraso (
    String codigoGrupo,
    String codigoCota,
    String versao,
    Double valorCredito,
    Double valorParcela,
    Double valorAtraso
) {

    public DadosCotaAtraso(Atraso atraso) {
        this(
            atraso.getCodigoGrupo(), 
            atraso.getCodigoCota(), 
            atraso.getVersao(), 
            atraso.getValorCredito(), 
            atraso.getValorParcela(), 
            atraso.getValorAtraso()
        );
    }

}
