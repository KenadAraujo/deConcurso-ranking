package com.polligonalapps.deconcurso.ranking.service;

import com.polligonalapps.deconcurso.ranking.dto.Organizadora;
import com.polligonalapps.deconcurso.ranking.repository.OrganizadoraRepository;
import com.polligonalapps.deconcurso.ranking.service.mapper.OrganizadoraMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrganizadoraServiceTest {

    @Mock
    private OrganizadoraMapper mapper;

    @Mock
    private OrganizadoraRepository repository;

    @InjectMocks
    private OrganizadoraService service;

    @Test
    public void deve_cadastrar(){
        Organizadora dto = new Organizadora("Centro brasileiro de pesquisa em avaliação e seleção e de promoção de eventos", "Cebraspe");
        var model = new com.polligonalapps.deconcurso.ranking.models.Organizadora(1L, "Centro brasileiro de pesquisa em avaliação e seleção e de promoção de eventos", "Cebraspe", "https://www.cebraspe.org.br/", null);

        when(repository.save(any())).thenReturn(model);
        when(mapper.toEntity(any())).thenReturn(model);
        when(mapper.toDto(any())).thenReturn(dto);

        var organizadoraCadastrada = service.salvarOuAtualizar(dto);
        Assert.notNull(organizadoraCadastrada,"A organizadora não pode ser nula");
    }
}
