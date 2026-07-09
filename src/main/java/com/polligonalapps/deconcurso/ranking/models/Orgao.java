package com.polligonalapps.deconcurso.ranking.models;

import com.polligonalapps.deconcurso.ranking.models.constants.AreaGoverno;
import com.polligonalapps.deconcurso.ranking.models.constants.Esfera;
import com.polligonalapps.deconcurso.ranking.models.constants.Estados;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "orgao")
public class Orgao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 255)
    private String sigla;

    @Column(length = 255)
    private String url;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private Esfera esfera;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private Estados estados;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private AreaGoverno areaGoverno;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "logo", columnDefinition = "BYTEA")
    private byte[] logo;

    @OneToMany(mappedBy = "orgao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Cargo> cargos = new HashSet<>();

    @OneToMany(mappedBy = "orgao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Concurso> concursos = new HashSet<>();
}
