package com.polligonalapps.deconcurso.ranking.models.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCota {

    NEGROS_E_PARDOS("Negros e pardos"),
    MULHERES("Mulheres"),
    DEFICIENTES("PCD's"),
    INDIGENAS("Indígenas"),
    TRANS("Transexuais");

    private String descricao;
}
