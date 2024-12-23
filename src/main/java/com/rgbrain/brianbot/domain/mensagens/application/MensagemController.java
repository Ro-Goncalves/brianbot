package com.rgbrain.brianbot.domain.mensagens.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MessageUseCase;

public class MensagemController {

    private final MessageUseCase messageUseCase;

    public MensagemController(MessageUseCase messageUseCase) {
        this.messageUseCase = messageUseCase;
    }

    @PostMapping("/messages-upsert")
    public ResponseEntity<Void> messagesUpsert(@RequestBody JsonNode payload) {
        // Logic to convert payload to Message object and call the use case
        Mensagem message = new Mensagem();
        // Set properties of message from payload
        // message.setProperty(payload.get("property").asText());
        
        messageUseCase.postMessagesUpsert(message);
        return ResponseEntity.ok().build();
    }
}