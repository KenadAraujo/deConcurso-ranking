package com.polligonalapps.deconcurso.ranking.service.mapper;

import com.polligonalapps.deconcurso.ranking.dto.Organizadora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

@ExtendWith(MockitoExtension.class)
public class OrganizadoraMapperTest {

    private OrganizadoraMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = OrganizadoraMapper.INSTANCE;
    }

    @Test
    public void toEntity_deveConverter(){
        var dto = new Organizadora();
        var model = mapper.toEntity(dto);
        Assert.notNull(model,"A organizadora não pode ser nula");
    }

    @Test
    public void toDto_deveConverter(){
        var model = new com.polligonalapps.deconcurso.ranking.models.Organizadora();
        var dto = mapper.toDto(model);
        Assert.notNull(dto,"A organizadora não pode ser nula");
    }
}
