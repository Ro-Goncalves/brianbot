package com.rgbrain.brianbot.domain.atraso;

import java.util.List;

import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;
import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Consorciado;
import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Cota;

public class AtrasoDados {
    public static List<Atraso> criarListaAtraso() {
        return List.of(
            Atraso.builder()
                .consorciado(
                    Consorciado.builder()
                        .nomeConsorciado("Consorciado 01")
                        .whatsAppConsorciado("Telefone 01")
                        .emailConsorciado("Email 01")
                        .build())
                .cota(
                    Cota.builder()
                        .codigoGrupo("Grupo 01")
                        .codigoCota("Cota 01")
                        .versao("Versao 01")
                        .valorCredito(500.00)
                        .valorParcela(50.00)
                        .build())                
                .valorAtraso(10.00)
                .build(),
            Atraso.builder()
                .consorciado(
                    Consorciado.builder()
                        .nomeConsorciado("Consorciado 02")
                        .whatsAppConsorciado("Telefone 02")
                        .emailConsorciado("Email 02")
                        .build()) 
                .cota(
                    Cota.builder()
                        .codigoGrupo("Grupo 02")
                        .codigoCota("Cota 01")
                        .versao("Versao 01")
                        .valorCredito(500.00)
                        .valorParcela(50.00)
                        .build())                
                .valorAtraso(10.00)
                .build(),
            Atraso.builder()
                .consorciado(
                    Consorciado.builder()
                        .nomeConsorciado("Consorciado 02")
                        .whatsAppConsorciado("Telefone 02")
                        .emailConsorciado("Email 02")
                        .build()) 
                .cota(
                    Cota.builder()
                        .codigoGrupo("Grupo 02")
                        .codigoCota("Cota 02")
                        .versao("Versao 01")
                        .valorCredito(500.00)
                        .valorParcela(50.00)
                        .build())                
                .valorAtraso(10.00)
                .build()
        );
    }
}
