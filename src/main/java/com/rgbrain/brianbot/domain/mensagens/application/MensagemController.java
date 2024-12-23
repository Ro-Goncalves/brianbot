package com.rgbrain.brianbot.domain.mensagens.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MessageUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/brianbot")
@RequiredArgsConstructor
public class MensagemController {

    private static final Logger logger = LoggerFactory.getLogger(MensagemController.class);

    @Qualifier("MessageUseCase")
    private final MessageUseCase messageUseCase;    

    @PostMapping("/messages-upsert")
    public ResponseEntity<Void> messagesUpsert(@RequestBody JsonNode payload) {       
        Mensagem message = new Mensagem();
        
        messageUseCase.postMessagesUpsert(message);
        return ResponseEntity.ok().build();
    }
}