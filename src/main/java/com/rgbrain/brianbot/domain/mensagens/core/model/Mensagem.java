package com.rgbrain.brianbot.domain.mensagens.core.model;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "mensagens")
@Entity(name = "Mensagem")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String idMensagem;
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
    private Comando comando;

    public Mensagem(DadosMensagem dadosMensagem) {
        this.idMensagem = dadosMensagem.getData().getKey().getId();
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

            var comandoStr = this.mensagem.trim().split(" ");
            var comando = comandoStr[1];

            var parametros = comando.split("-");
            var dominioComando = parametros[0];
            var parametrosComando = List.of(Arrays.copyOfRange(parametros, 1, parametros.length));

            this.comando = new Comando(comando, dominioComando, parametrosComando);

            // Atualiza o campo mensagem removendo o comando
            this.mensagem = this.mensagem.replace("/BrianBot " + comando, "").trim();
        }
    }
    
    public static record Comando(String comando, String dominioComando, List<String> parametrosComando) {}
}
