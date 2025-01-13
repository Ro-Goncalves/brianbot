package com.rgbrain.brianbot.domain.mensagens.core.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @AllArgsConstructor
    @Builder
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
        @Override
        public String toString() {
            return "MessageData [key=" + key + ", pushName=" + pushName + ", status=" + status + ", message=" + message
                    + ", messageType=" + messageType + ", messageTimestamp=" + messageTimestamp + ", instanceId="
                    + instanceId + ", source=" + source + "]";
        }
        
    }
    
    @Embeddable
    @Getter
    @AllArgsConstructor
    @Builder
    public static class MessageKey {
        private String remoteJid;
        private Boolean fromMe;
        private String id;
        @Override
        public String toString() {
            return "MessageKey [remoteJid=" + remoteJid + ", fromMe=" + fromMe + ", id=" + id + "]";
        }
    }
    
    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MessageContent {
        private String conversation;

        @Override
        public String toString() {
            return "MessageContent [conversation=" + conversation + "]";
        }
    }

    @Override
    public String toString() {
        return "DadosMensagem [idMensagem=" + idMensagem + ", event=" + event + ", instance=" + instance + ", data="
                + data + ", destination=" + destination + ", date_time=" + date_time + ", sender=" + sender
                + ", server_url=" + server_url + ", apikey=" + apikey + "]";
    }
}