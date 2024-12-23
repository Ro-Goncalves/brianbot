package com.rgbrain.brianbot.domain.mensagens.core.port.outgoing;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;

public interface MessageRepository extends JpaRepository<Mensagem, Long> {
    
}