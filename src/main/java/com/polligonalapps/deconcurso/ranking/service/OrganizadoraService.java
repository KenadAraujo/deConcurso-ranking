package com.polligonalapps.deconcurso.ranking.service;

import com.polligonalapps.deconcurso.ranking.dto.Organizadora;
import com.polligonalapps.deconcurso.ranking.repository.OrganizadoraRepository;
import com.polligonalapps.deconcurso.ranking.repository.specification.OrganizadoraSpecification;
import com.polligonalapps.deconcurso.ranking.service.mapper.OrganizadoraMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrganizadoraService {

    @Autowired
    private OrganizadoraMapper mapper;

    @Autowired
    private OrganizadoraRepository repository;

    public Organizadora salvarOuAtualizar(Organizadora organizadora) {
        var organizadoraASerCadastrada = mapper.toEntity(organizadora);
        var organizadoraCadastrada = repository.save(organizadoraASerCadastrada);
        return mapper.toDto(organizadoraCadastrada);
    }

    public Organizadora buscarPorId(Integer id) {
        var organizadoraEncontrada = repository.findById(Long.valueOf(id));
        return organizadoraEncontrada.map(organizadora -> mapper.toDto(organizadora)).orElse(null);
    }

    public Organizadora excluir(Organizadora organizadoraEncontrada) {
        var organizadora = mapper.toEntity(organizadoraEncontrada);
        repository.delete(organizadora);
        return organizadoraEncontrada;
    }

    public List<Organizadora> listar(Organizadora organizadora) {
        log.info("[ORGANIZADORA SERVICE] Buscando por parametros {}", organizadora);
        var organizadoraBusca = mapper.toEntity(organizadora);
        var organizadoras = repository.findAll(OrganizadoraSpecification.filtrar(organizadoraBusca));
        return mapper.toDtoList(organizadoras);
    }
}
