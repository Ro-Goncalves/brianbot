package com.rgbrain.brianbot.domain.mensagens.core;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rgbrain.brianbot.domain.mensagens.core.model.Dominios;
import com.rgbrain.brianbot.domain.mensagens.core.model.Mensagem;
import com.rgbrain.brianbot.domain.mensagens.core.model.RespostaEvent;
import com.rgbrain.brianbot.domain.mensagens.core.port.incoming.MessageUseCase;
import com.rgbrain.brianbot.domain.mensagens.core.port.outgoing.MensagemEventPublisher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagemFacade implements MessageUseCase {

    private static final Logger logger = LoggerFactory.getLogger(MessagemFacade.class);
    
    private final MensagemEventPublisher mensagemEventPublisher;

    @Override
    public void postMessagesUpsert(Mensagem mensagem) {
        if(!mensagem.getIsComando()) {
            logger.debug("Não processa mensagem que não é comando");
            return;
        }

        var dominioInvalido = 
            Arrays.stream(Dominios.values())
                .noneMatch(
                    dominio -> dominio.name().equalsIgnoreCase(mensagem.getDominioComando())
                );

        if(dominioInvalido) {
            logger.info("Dominio Ausente ou Inválido: {}", mensagem.getDominioComando());

            var mensagemResposta = """
            Olá %s!
            Será um prazer *INOMINÁVEL* lhe ajudar! 🕺.
            Sou o */BrianBot*, seu assistente virtual mais charmoso, engenhoso e (modéstia à parte) maravilhoso. 💁‍♂️\n
            Atualmente, eu posso ajudar com estas funcionalidades incríveis:
            %s\n
            Se precisar de detalhes sobre um domínio específico, basta dizer:
            */BrianBot [domínio]*\n
            *PREPARE-SE PARA MINHA SABEDORIA (E MINHAS PIADAS RUINS), E SEJA BEM-VINDO AO MUNDO DO BRIANBOT! ✨*\n
            """.formatted(
                mensagem.getNomeRemetente(),
                Dominios.dominiosDisponiveis()
            );

            var evento = new RespostaEvent(
                mensagem.getInstancia(), 
                mensagem.getIdRemoto(), 
                mensagemResposta,
                mensagem.getIdMensagem()
            );
            logger.debug("Publicando evento para Mensagem de Resposta: {}", evento);
            mensagemEventPublisher.publicar(evento);
        }
    }
}