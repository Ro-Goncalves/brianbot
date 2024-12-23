package com.rgbrain.brianbot.domain.mensagens.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;

import java.util.List;
import java.util.Date;

/**
 * Repositório responsável por operações de persistência para a entidade Mensagem.
 */
@Repository
public interface MessageRepository extends JpaRepository<Mensagem, Long> {

    /**
     * Encontra todas as mensagens ativas criadas após a data especificada.
     *
     * @param dataCriacao a data de criação a partir da qual as mensagens devem ser retornadas
     * @return uma lista de mensagens ativas criadas após a data especificada
     */
    List<Mensagem> findByAtivoTrueAndDataCriacaoAfter(Date dataCriacao);

    /**
     * Encontra todas as mensagens respondidas criadas após a data especificada.
     *
     * @param dataCriacao a data de criação a partir da qual as mensagens devem ser retornadas
     * @return uma lista de mensagens respondidas criadas após a data especificada
     */
    List<Mensagem> findByRespostaTrueAndDataCriacaoAfter(Date dataCriacao);
}

