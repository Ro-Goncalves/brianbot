package com.rgbrain.brianbot.domain.mensagens.domain.model;

import static com.rgbrain.brianbot.domain.mensagens.MensagemTesteSuporte.criarDadosMensagem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;

public class MensagemTest {    

    @Test
    void quandoAtivacaoNaoConterComando_deveCriarComandoSemDominioAcaoEParametros() {
        // given
        var dadosMensagem = criarDadosMensagem("/BrianBot");        

        // when
        var mensagem = new Mensagem(dadosMensagem);
        
        // then
        assertThat(mensagem.getIsAtivacao(), is(Boolean.TRUE));

        var comando = mensagem.getComando();
        assertThat(comando.getDominio(), is(nullValue()));
        assertThat(comando.getAcao(), is(nullValue()));
        assertThat(comando.getParametros(), is(nullValue()));
         
    }

    @Test
    void quandoAtivacaoNaoAcao_deveCriarComandoSemAcaoEParametros() {
        // given
        var dadosMensagem = criarDadosMensagem("/BrianBot dominio");        

        // when
        var mensagem = new Mensagem(dadosMensagem);
        
        // then
        assertThat(mensagem.getIsAtivacao(), is(Boolean.TRUE));

        var comando = mensagem.getComando();
        assertThat(comando.getDominio(), is(equalTo("dominio")));
        assertThat(comando.getAcao(), is(nullValue()));
        assertThat(comando.getParametros(), is(nullValue()));
    }

    @Test
    void quandoAtivacaoNaoConterParametros_deveCriarComandoSemParametros() {
        // given
        var dadosMensagem = criarDadosMensagem("/BrianBot dominio-acao");        

        // when
        var mensagem = new Mensagem(dadosMensagem);
        
        // then
        assertThat(mensagem.getIsAtivacao(), is(Boolean.TRUE));

        var comando = mensagem.getComando();
        assertThat(comando.getDominio(), is(equalTo("dominio")));
        assertThat(comando.getAcao(), is(equalTo("acao")));
        assertThat(comando.getParametros(), is(nullValue()));
    }

    @Test
    void quandoAtivacaoConterTodosParametros_deveCriarComandoCompleto() {
        // given
        var dadosMensagem = criarDadosMensagem("/BrianBot dominio-acao-parametroUm-parametroDois-parametroTres");        

        // when
        var mensagem = new Mensagem(dadosMensagem);
        
        // then
        assertThat(mensagem.getIsAtivacao(), is(Boolean.TRUE));

        var comando = mensagem.getComando();
        assertThat(comando.getDominio(), is(equalTo("dominio")));
        assertThat(comando.getAcao(), is(equalTo("acao")));
        assertThat(comando.getParametros(), contains("parametroUm", "parametroDois", "parametroTres"));
    }
    
}
