package com.rgbrain.brianbot.domain.mensagens;

import com.rgbrain.brianbot.domain.mensagens.core.model.DadosMensagem;

public class MensagemTesteSuporte {
    
    public static DadosMensagem criarDadosMensagem(String conversation) {
        return 
            DadosMensagem
                .builder()
                .idMensagem(1L)
                .event("ativacao")
                .instance("instance")
                .destination("destination")
                .date_time("date_time")
                .sender("sender")
                .server_url("server_url")
                .apikey("apikey")                
                .data(
                    DadosMensagem.MessageData
                        .builder()
                        .pushName("pushName")
                        .status("status")
                        .messageType("messageType")
                        .messageTimestamp(1L)
                        .instanceId("instanceId")
                        .source("source")
                        .key(
                            DadosMensagem.MessageKey
                                .builder()
                                .remoteJid("remoteJid")
                                .fromMe(true)
                                .id("id")
                                .build()
                        )
                        .message(
                            DadosMensagem.MessageContent
                                .builder()
                                .conversation(conversation)
                                .build()
                        )
                        .build()
                )
                .build();        

    }
}
