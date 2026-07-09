package com.polligonalapps.deconcurso.ranking.service.mapper;

import com.polligonalapps.deconcurso.ranking.models.Organizadora;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizadoraMapper {

    OrganizadoraMapper INSTANCE = Mappers.getMapper(OrganizadoraMapper.class);

    @Mapping(source = "logo", target = "logo", qualifiedByName = "byteArrayToBase64")
    public com.polligonalapps.deconcurso.ranking.dto.Organizadora toDto(Organizadora organizadora);

    @Mapping(source = "logo", target = "logo", qualifiedByName = "base64ToByteArray")
    public Organizadora toEntity(com.polligonalapps.deconcurso.ranking.dto.Organizadora organizadora);

    List<com.polligonalapps.deconcurso.ranking.dto.Organizadora> toDtoList(List<Organizadora> organizadoras);

    List<Organizadora> toEntityList(List<com.polligonalapps.deconcurso.ranking.dto.Organizadora> organizadoras);


    @Named("byteArrayToBase64")
    default String byteArrayToBase64(byte[] bytes) {
        return bytes != null ? Base64.getEncoder().encodeToString(bytes) : null;
    }

    @Named("base64ToByteArray")
    default byte[] base64ToByteArray(String base64) {
        return base64 != null ? Base64.getDecoder().decode(base64) : null;
    }
}
