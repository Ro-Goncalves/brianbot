package com.rgbrain.brianbot.domain.mensagens.core.model;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "mensagens")
@Entity(name = "Mensagem")
@Getter
@NoArgsConstructor
@ToString(exclude = "idMensagem")
public class Mensagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensagem;
   
    private String evento;
    private String instancia;
    private String idRemoto;
    private Boolean enviadoPorMim;
    private String nomeRemetente;
    private String status;
    private String mensagem;
    private String tipoMensagem;
    private Long timestampMensagem;
    private String idInstancia;
    private String origem;
    private Boolean isComando;
    private String comando;
    private String dominioComando;
    private String[] parametrosComando;

    public Mensagem(DadosMensagem dadosMensagem) {
        this.evento = dadosMensagem.getEvent();
        this.instancia = dadosMensagem.getInstance();
        this.idRemoto = dadosMensagem.getData().getKey().getRemoteJid();
        this.enviadoPorMim = dadosMensagem.getData().getKey().getFromMe();
        this.nomeRemetente = dadosMensagem.getData().getPushName();
        this.status = dadosMensagem.getData().getStatus();
        this.mensagem = dadosMensagem.getData().getMessage().getConversation();
        this.tipoMensagem = dadosMensagem.getData().getMessageType();
        this.timestampMensagem = dadosMensagem.getData().getMessageTimestamp();
        this.idInstancia = dadosMensagem.getData().getInstanceId();
        this.origem = dadosMensagem.getData().getSource();
        this.isComando = Boolean.FALSE;

        /*
         * Verifica se a mensagem é um comando
         * Formato de um comando: /BrianBot comando
         * Onde comando dominio-parametros1-parametros2-...-parametrosN
         * Exemplo: /BrianBot clima-londrina
         */
        if(this.mensagem.startsWith("/BrianBot")) {
            this.isComando = Boolean.TRUE;

            var comando = this.mensagem.trim().split(" ");
            this.comando = comando[1];

            var parametros = comando[1].split("-");
            this.dominioComando = parametros[0];
            this.parametrosComando = Arrays.copyOfRange(parametros, 1, parametros.length);

            // Atualiza o campo mensagem removendo o comando
            this.mensagem = this.mensagem.replace("/BrianBot " + this.comando, "").trim();
        }
    }
}
