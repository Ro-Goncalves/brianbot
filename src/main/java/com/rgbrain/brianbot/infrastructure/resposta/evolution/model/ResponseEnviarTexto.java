package com.rgbrain.brianbot.infrastructure.resposta.evolution.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseEnviarTexto {

    private Key key;
    private String pushName;
    private String status;
    private Message message;
    private Object contextInfo;
    private String messageType;
    private long messageTimestamp;
    private String instanceId;
    private String source;

    @Getter
    @ToString
    public static class Key {
        private String remoteJid;
        private boolean fromMe;
        private String id;
    }

    @Getter
    @ToString
    public static class Message {
        private String conversation;
    }
    
}
