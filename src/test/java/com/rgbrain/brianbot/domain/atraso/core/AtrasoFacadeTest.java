package com.rgbrain.brianbot.domain.atraso.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
// import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.rgbrain.brianbot.domain.atraso.core.model.command.ObterTodosAtrasosCommand;
import com.rgbrain.brianbot.domain.atraso.core.ports.outgoing.AtrasoDataBase;
import com.rgbrain.brianbot.domain.atraso.infrastructure.entity.Atraso;

public class AtrasoFacadeTest {

    private AtrasoDataBase atrasoDataBase;

    private AtrasoFacade atrasoFacade;

    @BeforeEach
    void setUp() throws Exception {
        atrasoDataBase = mock(AtrasoDataBase.class);
        atrasoFacade = new AtrasoFacade(atrasoDataBase);
    }

    @Test
    @DisplayName("Obter Todos Atrasos")
    void quandoLidarComObterTodosAtrasosCommand_deveRetornarListaDeDadosConsorciadoAtrasos() {
        //Given
        var command = new ObterTodosAtrasosCommand(1L);
        var atrasosComissionado = List.of(
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
        when(atrasoDataBase.obterAtrasos(Mockito.anyLong())).thenReturn(atrasosComissionado);

        //When
        var dadosConsorciadoAtrasos = atrasoFacade.handle(command);

        //Then
        assertThat(dadosConsorciadoAtrasos, hasSize(2));
        
        var primeiroConsorciado = dadosConsorciadoAtrasos.get(0);
        assertThat(primeiroConsorciado.nomeConsorciado(), is(equalTo("Consorciado 01")));
        assertThat(primeiroConsorciado.telefoneConsorciado(), is(equalTo("Telefone 01")));
        assertThat(primeiroConsorciado.emailConsorciado(), is(equalTo("Email 01")));
        assertThat(primeiroConsorciado.valorTotalCreditoAtraso(), is(equalTo(500.00)));
        assertThat(primeiroConsorciado.valorTotalParcelaAtraso(), is(equalTo(50.00)));
        assertThat(primeiroConsorciado.valorTotalAtraso(), is(equalTo(10.00)));


        var segundoConsorciado = dadosConsorciadoAtrasos.get(1);
        assertThat(segundoConsorciado.nomeConsorciado(), is(equalTo("Consorciado 02")));
        assertThat(segundoConsorciado.telefoneConsorciado(), is(equalTo("Telefone 02")));
        assertThat(segundoConsorciado.emailConsorciado(), is(equalTo("Email 02")));
        
        assertThat(primeiroConsorciado.valorTotalCreditoAtraso(), is(equalTo(1000.00)));
        assertThat(primeiroConsorciado.valorTotalParcelaAtraso(), is(equalTo(100.00)));
        assertThat(primeiroConsorciado.valorTotalAtraso(), is(equalTo(20.00)));

    }
}
