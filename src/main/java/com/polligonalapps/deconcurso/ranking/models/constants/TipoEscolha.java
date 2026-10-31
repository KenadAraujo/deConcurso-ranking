package com.polligonalapps.deconcurso.ranking.models.constants;

public enum TipoEscolha {

    CLASSIFICATORIA("Classificatória"),
    ELIMINATORIA("Eliminatória"),
    CLASSIFICATORIA_E_ELIMINATORIA("Classificatória e Eliminatória");

    private final String descricao;

    TipoEscolha(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
