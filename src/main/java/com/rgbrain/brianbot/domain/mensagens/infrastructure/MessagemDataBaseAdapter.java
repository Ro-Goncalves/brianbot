package com.rgbrain.brianbot.domain.mensagens.infrastructure;

import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemDataBase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagemDataBaseAdapter implements MensagemDataBase {

	private final MessageRepository messageRepository;
}