package com.rgbrain.brianbot.domain.mensagens.core.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

    private static final String ATIVACAO = "/BrianBot";

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
    private Boolean isAtivacao;

    @Transient
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
        this.isAtivacao = Boolean.FALSE;

        processarComando(this.mensagem);
    }

    private void processarComando(String mensagem) {
        if (!mensagem.startsWith(ATIVACAO)) {
            this.isAtivacao = Boolean.FALSE;
            return;
        }

        this.isAtivacao = Boolean.TRUE;

        var mensagemTokens = mensagem.trim().split(" ");
        // O comando é tudo aquilo que está depois do ATIVACAO
        var comandoToken = Optional.ofNullable(mensagemTokens)
            .filter(tokens -> tokens.length > 1)
            .map(tokens -> tokens[1])
            .orElse("");

        this.comando = Comando.criarComando(comandoToken);

        if (this.comando != null) {
            this.mensagem = mensagem.replace(ATIVACAO + " " + comandoToken, "").trim();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Comando {
        private String dominio;
        private String acao;
        private List<String> parametros;        

        public static Comando criarComando(String comandoToken) {
            // Se o comando for vazio, retorna um comando vazio
            if (comandoToken.isBlank()) {
                return new Comando(null, null, null);
            }

            var parametros = comandoToken.split("-");
            // O Domínio que deve ser chamado, é o primeiro token do comando
            var dominioComando = parametros[0];

            // Se o comando tiver apenas um token, retorna um comando com o domínio
            if (parametros.length == 1) {
                return new Comando(dominioComando, null, null);
            }

            // Se o comando tiver mais de um token, o segundo token é a ação que deve ser executada
            var acaoComando = parametros[1];
            // Se existir mais de dois tokens, os demais tokens serão os parâmetros
            var parametrosComando = parametros.length <= 2 ? 
                null : 
                List.of(Arrays.copyOfRange(parametros, 2, parametros.length));

            // Exemplo completo: /BrianBot dominio-acao-parametro1-parametro2
            return new Comando(dominioComando, acaoComando, parametrosComando);
        }

        public Boolean isDominioValido() {
            return Arrays.stream(Dominios.values())
                .anyMatch(
                    dominio -> dominio.name().equalsIgnoreCase(this.dominio)
                );
        }
    }
}
