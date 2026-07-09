package com.polligonalapps.deconcurso.ranking.repository;

import com.polligonalapps.deconcurso.ranking.models.Organizadora;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizadoraRepository extends CrudRepository<Organizadora, Long>, JpaSpecificationExecutor<Organizadora> {
}
