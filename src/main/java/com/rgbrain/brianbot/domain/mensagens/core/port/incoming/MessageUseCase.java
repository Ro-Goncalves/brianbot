package com.rgbrain.brianbot.domain.mensagens.core.port.incoming;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;

public interface MessageUseCase {
    void postMessagesUpsert(Mensagem mensagem);
}