package com.rgbrain.brianbot.domain.mensagens.core;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rgbrain.brianbot.domain.mensagens.core.model.Dominios;
import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.model.command.MensagemAjudaCommand;
import com.rgbrain.brianbot.domain.mensagens.core.model.command.MensagemDominioValidoCommand;
import com.rgbrain.brianbot.domain.mensagens.core.model.event.ComandoEvent;
import com.rgbrain.brianbot.domain.mensagens.core.model.event.RespostaEvent;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MensagemAjuda;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MensagemDominioValido;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemEventPublisher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagemFacade implements MensagemAjuda, MensagemDominioValido {

    private static final Logger logger = LoggerFactory.getLogger(MessagemFacade.class);
    
    private final MensagemEventPublisher mensagemEventPublisher;

    @Override
    public void handle(MensagemDominioValidoCommand command) {
        logger.info(
            "Dominio Válido: Dominio: {} - Ação: {} - Parametros: {}", 
            command.dominio(), command.acao(), command.parametros());

        var evento = new ComandoEvent(command);
        logger.debug("Publicando evento para Comando: {}", evento);
        mensagemEventPublisher.publicar(evento);
    }

    @Override
    public void handle(MensagemAjudaCommand command) {
        // logger.info("Dominio Ausente ou Inválido: {}", mensagem.getComando().getDominio());

        // var mensagemResposta = """
        // Olá %s!
        // Será um prazer *INOMINÁVEL* lhe ajudar! 🕺.
        // Sou o */BrianBot*, seu assistente virtual mais charmoso, engenhoso e (modéstia à parte) maravilhoso. 💁‍♂️\n
        // Atualmente, eu posso ajudar com estas funcionalidades incríveis:
        // %s\n
        // Se precisar de detalhes sobre um domínio específico, basta dizer:
        // */BrianBot [domínio]*\n
        // *PREPARE-SE PARA MINHA SABEDORIA (E MINHAS PIADAS RUINS), E SEJA BEM-VINDO AO MUNDO DO BRIANBOT! ✨*\n
        // """.formatted(
        //     mensagem.getNomeRemetente(),
        //     Dominios.dominiosDisponiveis()
        // );

        // var evento = new RespostaEvent(
        //     mensagem.getInstancia(), 
        //     mensagem.getIdRemoto(), 
        //     mensagemResposta,
        //     mensagem.getIdMensagem()
        // );
        // logger.debug("Publicando evento para Resposta: {}", evento);
        // mensagemEventPublisher.publicar(evento);
    }
}