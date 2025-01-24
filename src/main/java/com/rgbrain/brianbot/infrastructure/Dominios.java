package com.rgbrain.brianbot.infrastructure;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Dominios {
    CLIMA("🌞☔", "Clima", "Quer saber se precisa levar o guarda-chuva ou passar o protetor solar? Eu sou praticamente um meteorologista virtual — só que mais bonito e sem diploma."), 
    GRAMATICA("📚✍️", "Gramática", "Eu sou tipo aquele professor chato, mas sem a chatice que chateia! Quer arrasar na concordância e impressionar no português? Deixe comigo!");

    private final String emoji;
    private final String nome;
    private final String descricao;

    Dominios(String emoji, String nome, String descricao) {
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

    public static String dominiosDisponiveis() {
        return Arrays.stream(Dominios.values())
            .map(dominio -> dominio.getEmoji() + " *" + dominio.getNome() + "*: " + dominio.getDescricao())
            .collect(Collectors.joining("\n"));
    }
}
