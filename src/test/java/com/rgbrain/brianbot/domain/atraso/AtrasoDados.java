package com.rgbrain.brianbot.domain.atraso;

import java.util.List;

import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;

public class AtrasoDados {
    public static List<Atraso> criarListaAtraso() {
        return List.of(
            Atraso.builder()
                .nomeConsorciado("Consorciado 01")
                .telefoneConsorciado("Telefone 01")
                .emailConsorciado("Email 01")
                .codigoGrupo("Grupo 01")
                .codigoCota("Cota 01")
                .versao("Versao 01")
                .valorCredito(500.00)
                .valorParcela(50.00)
                .valorAtraso(10.00)
                .build(),
            Atraso.builder()
                .nomeConsorciado("Consorciado 02")
                .telefoneConsorciado("Telefone 02")
                .emailConsorciado("Email 02")
                .codigoGrupo("Grupo 02")
                .codigoCota("Cota 01")
                .versao("Versao 01")
                .valorCredito(500.00)
                .valorParcela(50.00)
                .valorAtraso(10.00)
                .build(),
            Atraso.builder()
                .nomeConsorciado("Consorciado 02")
                .telefoneConsorciado("Telefone 02")
                .emailConsorciado("Email 02")
                .codigoGrupo("Grupo 02")
                .codigoCota("Cota 02")
                .versao("Versao 01")
                .valorCredito(500.00)
                .valorParcela(50.00)
                .valorAtraso(10.00)
                .build()
        );
    }
}
