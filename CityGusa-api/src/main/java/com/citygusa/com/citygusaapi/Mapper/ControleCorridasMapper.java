package com.citygusa.com.citygusaapi.Mapper;

import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ControleCorridasMapper {

    ControleCorridasMapper INSTANCER = Mappers.getMapper(ControleCorridasMapper.class);

    ControleDeCorridasDto toDto(ControleCorridasEntity entity);
    ControleCorridasEntity toEntity(ControleDeCorridasDto dto);
}
