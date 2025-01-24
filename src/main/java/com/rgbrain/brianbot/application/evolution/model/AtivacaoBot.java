package com.rgbrain.brianbot.application.evolution.model;

public enum AtivacaoBot {
    BRIAN_BOT("@BrianBot");

    private String ativacao;

    AtivacaoBot(String ativacao) {
        this.ativacao = ativacao;
    }

    public String getAtivacao() {
        return ativacao;
    }
}
