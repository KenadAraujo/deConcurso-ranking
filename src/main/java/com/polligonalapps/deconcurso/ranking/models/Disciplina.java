package com.polligonalapps.deconcurso.ranking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String descricao;

    @Column(name = "pontuacao_minima",nullable = false)
    private Integer pontuacaoMinima;

    @Column(name = "pontuacao_maxima",nullable = false)
    private Integer pontuacaoMaxima;

    @Column(name = "reprovar_por_pontuacao_minima", nullable = false)
    private boolean reprovarPorPontuacaoMinima;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prova_id", nullable = false)
    private Prova prova;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_disciplina_id", nullable = false)
    private GrupoDisciplina grupoDisciplina;
}

