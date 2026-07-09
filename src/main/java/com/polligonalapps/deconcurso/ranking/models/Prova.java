package com.polligonalapps.deconcurso.ranking.models;

import com.polligonalapps.deconcurso.ranking.models.constants.TipoProva;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prova")
public class Prova {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "pontuacao_minima")
    private Integer pontuacaoMinima;

    @Column(name = "pontuacao_maxima")
    private Integer pontuacaoMaxima;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_prova",length = 20)
    private TipoProva tipoProva;

    //TODO tipo de escolha
    //TODO erradas anulam prova
    //TODO calculo especial

    @OneToMany(mappedBy = "prova", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<GrupoDisciplina> gruposDisciplinas;

    @OneToMany(mappedBy = "prova", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Disciplina> disciplinas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;
}
