package com.rgbrain.brianbot.domain.mensagens.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;

@Repository
public interface MessageRepository extends JpaRepository<Mensagem, Long> {
    
}