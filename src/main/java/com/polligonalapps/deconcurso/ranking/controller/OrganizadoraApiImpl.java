package com.polligonalapps.deconcurso.ranking.controller;

import com.polligonalapps.deconcurso.ranking.dto.Organizadora;
import com.polligonalapps.deconcurso.ranking.service.OrganizadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class OrganizadoraApiImpl implements OrganizadoraApi{

    @Autowired
    private OrganizadoraService service;

    @Override
    public ResponseEntity<List<Organizadora>> listar(String nome, String sigla) {
        Organizadora organizadora = new Organizadora();
        if(Objects.nonNull(nome)){
            organizadora.setNome(nome);
        }
        if(Objects.nonNull(sigla)){
            organizadora.setSigla(sigla);
        }
        return ResponseEntity.ok(service.listar(organizadora));
    }

    @Override
    public ResponseEntity<Organizadora> cadastrar(Organizadora organizadora) {
        Organizadora organizadoraCadastrada = service.salvarOuAtualizar(organizadora);
        return ResponseEntity.ok(organizadoraCadastrada);
    }

    @Override
    public ResponseEntity<Organizadora> atualizar(Integer id, Organizadora organizadora) {
        Organizadora organizadoraAtualizada = service.salvarOuAtualizar(organizadora);
        return ResponseEntity.ok(organizadoraAtualizada);
    }

    @Override
    public ResponseEntity<Organizadora> buscarPorId(Integer id) {
        Organizadora organizadoraEncontrada = service.buscarPorId(id);
        if (Objects.isNull(organizadoraEncontrada)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(organizadoraEncontrada);
    }

    @Override
    public ResponseEntity<Void> excluir(Integer id) {
        Organizadora organizadoraEncontrada = service.buscarPorId(id);
        if (Objects.isNull(organizadoraEncontrada)){
            return ResponseEntity.badRequest().build();
        }
        service.excluir(organizadoraEncontrada);
        return ResponseEntity.accepted().build();
    }
}
