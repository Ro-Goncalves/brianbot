package com.rgbrain.brianbot.domain.atraso.core.model.dados;

import java.util.ArrayList;
import java.util.List;

import com.rgbrain.brianbot.domain.atraso.infrastructure.entity.Atraso;

public record DadosConsorciadoAtrasos (
    String nomeConsorciado,
    String telefoneConsorciado,
    String emailConsorciado,    
    List<DadosCotaAtraso> cotasAtraso
) {

    public DadosConsorciadoAtrasos(Atraso atraso, DadosCotaAtraso dadosAtrasoCota) {
        this(
            atraso.getNomeConsorciado(),
            atraso.getTelefoneConsorciado(),
            atraso.getEmailConsorciado(),           
            List.of(dadosAtrasoCota)
        );
    }

    public DadosConsorciadoAtrasos(Atraso atraso) {
        this(
            atraso.getNomeConsorciado(),
            atraso.getTelefoneConsorciado(),
            atraso.getEmailConsorciado(),           
            new ArrayList<DadosCotaAtraso>()
        );
    }

    public void adicionarCotaAtraso(DadosCotaAtraso dadosCotaAtraso) {
        this.cotasAtraso.add(dadosCotaAtraso);
    }

    public Double valorTotalCreditoAtraso() {
        return cotasAtraso
            .stream()
            .mapToDouble(DadosCotaAtraso::valorCredito)
            .sum();
    }    

    public Double valorTotalParcelaAtraso() {
        return cotasAtraso
            .stream()
            .mapToDouble(DadosCotaAtraso::valorParcela)
            .sum();
    }

    public Double valorTotalAtraso() {
        return cotasAtraso
            .stream()
            .mapToDouble(DadosCotaAtraso::valorAtraso)
            .sum();
    }
}
