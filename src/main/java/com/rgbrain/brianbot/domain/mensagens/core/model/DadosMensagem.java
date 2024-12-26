package com.rgbrain.brianbot.domain.mensagens.core.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DadosMensagem {
   
    private Long idMensagem;
    
    private String event;
    private String instance;
    
    @Embedded
    private MessageData data;
    
    private String destination;
    private String date_time;
    private String sender;
    private String server_url;
    private String apikey;
    
    @Embeddable
    @Getter
    @NoArgsConstructor
    public static class MessageData {
        @Embedded
        private MessageKey key;
        private String pushName;
        private String status;
        
        @Embedded
        private MessageContent message;
        
        private String messageType;
        private Long messageTimestamp;
        private String instanceId;
        private String source;
    }
    
    @Embeddable
    @Getter
    @NoArgsConstructor
    public static class MessageKey {
        private String remoteJid;
        private Boolean fromMe;
        private String id;
    }
    
    @Embeddable
    @Getter
    @NoArgsConstructor
    public static class MessageContent {
        private String conversation;
    }
}