package com.rgbrain.brianbot.domain.mensagens.core.port.incoming;

import com.rgbrain.brianbot.domain.mensagens.core.model.command.MensagemAjudaCommand;

public interface MensagemAjuda {
    void handle(MensagemAjudaCommand command);
}