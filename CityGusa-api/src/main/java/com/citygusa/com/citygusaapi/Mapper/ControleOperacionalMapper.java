package com.citygusa.com.citygusaapi.Mapper;

import com.citygusa.com.citygusaapi.Dto.ControleOperacionalDto;
import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ControleOperacionalMapper {

    ControleOperacionalDto toDto(ControleOperacionalEntity entity);
    ControleOperacionalEntity toEntity(ControleOperacionalDto dto);


}
