package com.rgbrain.brianbot.domain.clima.core.model;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.rgbrain.brianbot.domain.mensagens.core.model.Dominios;

public enum ClimaAcoes {
    CONSULTAR("🌞☔", "Consultar", "Quer saber se precisa levar o guarda-chuva ou passar o protetor solar? Eu sou praticamente um meteorologista virtual — só que mais bonito e sem diploma."), 
    REGISTAR("📚✍️", "Registar", "Eu sou tipo aquele professor chato, mas sem a chatice que chateia! Quer arrasar na concordância e impressionar no português? Deixe comigo!"),
    OBTER_PREVISAO("📚✍️", "ObterPrevisão", "Eu sou tipo aquele professor chato, mas sem a chatice que chateia! Quer arrasar na concordância e impressionar no português? Deixe comigo!");

    private final String emoji;
    private final String nome;
    private final String descricao;

    ClimaAcoes(String emoji, String nome, String descricao) {
        this.emoji = emoji;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static String acoesDisponiveis() {
        return Arrays.stream(Dominios.values())
            .map(acoes -> acoes.getEmoji() + " *" + acoes.getNome() + "*: " + acoes.getDescricao())
            .collect(Collectors.joining("\n"));
    }
}
