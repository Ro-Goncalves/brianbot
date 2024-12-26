package com.rgbrain.brianbot.domain.mensagens.core.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "mensagens")
@Entity(name = "Mensagem")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "idMensagem")
@ToString(exclude = "idMensagem")
public class Mensagem {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensagem;

    @JsonAlias("event")
    private String evento;

    @JsonAlias("instance")
    private String instancia;

    @JsonAlias("data.key.remoteJid")
    private String idRemoto;

    @JsonAlias("fromMe")
    private Boolean enviadoPorMim;

    @JsonAlias("poshName")
    private String nomeRemetente;

    @JsonAlias("status")    
    private String status;

    @JsonAlias("conversation")
    private String mensagem;

    @JsonAlias("messageType")
    private String tipoMensagem;

    @JsonAlias("messageTimestamp")
    private Long timestampMensagem;

    @JsonAlias("instanceId")
    private String idInstancia;

    @JsonAlias("source")
    private String origem;
}