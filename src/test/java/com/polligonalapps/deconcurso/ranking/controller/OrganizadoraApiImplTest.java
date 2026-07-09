package com.polligonalapps.deconcurso.ranking.controller;

import com.polligonalapps.deconcurso.ranking.dto.Organizadora;
import com.polligonalapps.deconcurso.ranking.service.OrganizadoraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrganizadoraApiImplTest {

    @Mock
    private OrganizadoraService service;

    @InjectMocks
    private OrganizadoraApiImpl controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Teste para o método listar()
    @Test
    void deveListarOrganizadoras() {
        ResponseEntity<List<Organizadora>> resposta = controller.listar(null);

        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertTrue(resposta.getBody().isEmpty());
    }

    // Teste para o método cadastrar()
    @Test
    void deveCadastrarOrganizadora() {
        Organizadora nova = new Organizadora();
        nova.setNome("Banca X");

        Organizadora cadastrada = new Organizadora();
        cadastrada.setNome("Banca X");

        when(service.salvarOuAtualizar(nova)).thenReturn(cadastrada);

        ResponseEntity<Organizadora> resposta = controller.cadastrar(nova);

        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals("Banca X", resposta.getBody().getNome());

        verify(service, times(1)).salvarOuAtualizar(nova);
    }

    // Teste para o método atualizar()
    @Test
    void deveAtualizarOrganizadora() {
        ResponseEntity<Organizadora> resposta = controller.atualizar(1, new Organizadora());
        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    // Teste para o método buscarPorId()
    @Test
    void deveBuscarOrganizadoraPorId() {
        doReturn(new Organizadora()).when(service).buscarPorId(any());
        ResponseEntity<Organizadora> resposta = controller.buscarPorId(1);
        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCodeValue());
    }

    // Teste para o método excluir()
    @Test
    void deveExcluirOrganizadora() {
        doReturn(new Organizadora()).when(service).buscarPorId(any());
        ResponseEntity<Void> resposta = controller.excluir(1);
        assertNotNull(resposta);
        assertEquals(202, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }
}
