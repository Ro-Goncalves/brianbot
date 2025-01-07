package com.rgbrain.brianbot.domain.mensagens.core.port.incoming;

import com.rgbrain.brianbot.domain.mensagens.core.model.command.MensagemDominioValidoCommand;

public interface MensagemDominioValido {
    void handle(MensagemDominioValidoCommand command);
}
