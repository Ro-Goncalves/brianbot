package com.rgbrain.brianbot.domain.mensagens.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.model.command.MensagemAjudaCommand;
import com.rgbrain.brianbot.domain.mensagens.core.model.command.MensagemDominioValidoCommand;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MensagemDominioValido;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MensagemAjuda;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/brianbot")
@RequiredArgsConstructor
public class MensagemController {

    private static final Logger logger = LoggerFactory.getLogger(MensagemController.class);

    @Qualifier("MensagemAjuda")
    private final MensagemAjuda mensagemAjuda;

    @Qualifier("MensagemDominioValido")
    private final MensagemDominioValido mensagemDominioValido;

    @PostMapping("/messages-upsert")
    public void messagesUpsert(@RequestBody JsonNode request) {       
        logger.debug("Recebendo mensagem: {}", request);
        
        var mensagem = new Mensagem(request);

        if (!mensagem.getIsAtivacao()) {
            logger.debug("Mensagem não é comando de ativação");
            return;
        }

        if (mensagem.getComando().isDominioValido()) {
            mensagemDominioValido.handle(new MensagemDominioValidoCommand(mensagem));
            return;
        }
        
        mensagemAjuda.handle(new MensagemAjudaCommand(mensagem));
        return;
    }
}