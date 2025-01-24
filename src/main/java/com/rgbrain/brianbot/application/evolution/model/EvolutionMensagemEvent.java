package com.rgbrain.brianbot.application.evolution.model;

import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.rgbrain.brianbot.infrastructure.Dominios;

public record EvolutionMensagemEvent(
    String idMensagem,
    String evento,
    String instancia,
    String idRemoto,
    Boolean enviadoPorMim,
    String nomeRemetente,
    String status,
    String mensagem,
    String tipoMensagem,
    Long timestampMensagem,
    String idInstancia,
    String origem,
    Boolean isAtivacao,
    String dominio
) {
    public EvolutionMensagemEvent(JsonNode request) {       
        this(
            request.get("data").get("key").get("id").asText(),
            request.get("event").asText(),
            request.get("instance").asText(),  
            request.get("data").get("key").get("remoteJid").asText(),
            request.get("data").get("key").get("fromMe").asBoolean(),
            request.get("data").get("pushName").asText(),
            request.get("data").get("status").asText(),
            request.get("data").get("message").get("conversation").asText(),
            request.get("data").get("messageType").asText(),
            request.get("data").get("messageTimestamp").asLong(),
            request.get("data").get("instanceId").asText(),
            request.get("data").get("source").asText(),
            mensagemAtivacao(request.get("data").get("message").get("conversation").asText()),
            obterDominio(request.get("data").get("message").get("conversation").asText())
        );        
    }

    private static Boolean mensagemAtivacao(String mensagem) {
        return mensagem.startsWith(AtivacaoBot.BRIAN_BOT.getAtivacao());
    }

    private static String obterDominio(String mensagem) {
        if (!mensagem.startsWith(AtivacaoBot.BRIAN_BOT.getAtivacao())) {
            return null;
        }
        return mensagem.split(" ")[1];
    }

    public Boolean isDominioValido() {
        return Arrays.stream(Dominios.values()).anyMatch(
            dominio -> dominio.name().equalsIgnoreCase(this.dominio)
        );
    }
    
}
