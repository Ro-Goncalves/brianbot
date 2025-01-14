package com.rgbrain.brianbot.domain.saci.core;

import org.springframework.beans.factory.annotation.Value;

import com.rgbrain.brianbot.domain.saci.core.model.dados.DadosDetalheAtraso;

public class CriardorMensagemWhatsApp {

    @Value("${saci.mensagem.detalhe.atraso}")
    private String templateMensagemDetalheAtraso;

    public String criarMensagemDetalheAtraso(String nomeComissionado, DadosDetalheAtraso dadosDetalheAtraso) {
        return templateMensagemDetalheAtraso.formatted(
            nomeComissionado,
            dadosDetalheAtraso.quantidadeParcelasAtraso(),
            dadosDetalheAtraso.valorTotalParcelasAtraso(),
            dadosDetalheAtraso.valorTotalCreditoAtraso(),
            dadosDetalheAtraso.quantidadeParcelasAtrasoImoveis(),
            dadosDetalheAtraso.quantidadeParcelasAtrasoVeiculos(),
            dadosDetalheAtraso.quantidadeParcelasAtrasoServicos(),
            dadosDetalheAtraso.quantidadeAtrasiVencimento8(),
            dadosDetalheAtraso.quantidadeAtrasiVencimento16(),
            dadosDetalheAtraso.quantidadeAtrasiVencimento20()
        );
    }
    
}
