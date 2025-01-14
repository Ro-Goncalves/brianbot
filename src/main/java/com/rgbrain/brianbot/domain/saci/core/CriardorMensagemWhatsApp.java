package com.rgbrain.brianbot.domain.saci.core;

import java.io.IOException;

import com.rgbrain.brianbot.domain.saci.core.model.dados.DadosDetalheAtraso;
import com.rgbrain.brianbot.domain.saci.core.model.exception.SaciCriarMensagemException;

public class CriardorMensagemWhatsApp {

    public static String criarMensagemDetalheAtraso(String nomeComissionado, DadosDetalheAtraso dadosDetalheAtraso) {
        try {
            var templateMensagemDetalheAtraso = 
                new String(CriardorMensagemWhatsApp.class
                    .getResourceAsStream("/saci_templade_detalhe_ataso.txt")
                    .readAllBytes());

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
        } catch (IOException e) {
            throw new SaciCriarMensagemException("Erro ao criar mensagem detalhe atraso: %s".formatted(e.getMessage()), e);
        }
    }
    
}
