package com.rgbrain.brianbot.domain.atraso.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.rgbrain.brianbot.domain.atraso.AtrasoDados;
import com.rgbrain.brianbot.domain.atraso.core.model.command.ObterTodosAtrasosCommand;
import com.rgbrain.brianbot.domain.atraso.core.ports.outgoing.AtrasoDataBase;

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
        var atrasosComissionado = AtrasoDados.criarListaAtraso();
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
        assertThat(segundoConsorciado.valorTotalCreditoAtraso(), is(equalTo(1000.00)));
        assertThat(segundoConsorciado.valorTotalParcelaAtraso(), is(equalTo(100.00)));
        assertThat(segundoConsorciado.valorTotalAtraso(), is(equalTo(20.00)));

    }
}
