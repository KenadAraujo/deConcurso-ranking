package com.polligonalapps.deconcurso.ranking.repository.specification;

import com.polligonalapps.deconcurso.ranking.models.Organizadora;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizadoraSpecification {

    public static Specification<Organizadora> filtrar (Organizadora organizadora){
        return (root, query, cb) ->{
            List<Predicate> predicates = new ArrayList<>();
            if(Objects.nonNull(organizadora.getNome()) && !organizadora.getNome().isBlank()){
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + organizadora.getNome().toLowerCase() + "%"));
            }
            if(Objects.nonNull(organizadora.getSigla()) && !organizadora.getSigla().isBlank()){
                predicates.add(cb.like(cb.lower(root.get("sigla")), "%" + organizadora.getSigla().toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
