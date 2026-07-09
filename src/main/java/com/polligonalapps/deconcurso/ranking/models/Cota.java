package com.polligonalapps.deconcurso.ranking.models;

import com.polligonalapps.deconcurso.ranking.models.constants.TipoCota;
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
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
@Table(name = "cargo")
public class Cota  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_cota")
    @Enumerated(EnumType.STRING)
    private TipoCota tipo;

    @Column(name = "qtd_vagas_totais")
    private Integer qtdVagasTotais;

    @Column(name = "qtd_vagas_cotas")
    private Integer qtdVagasCotas;

    @Column(name = "porcentagem_legal")
    private Integer porcentagemLegal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;
}
