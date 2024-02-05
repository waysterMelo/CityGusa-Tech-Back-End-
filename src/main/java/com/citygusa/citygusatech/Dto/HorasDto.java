package com.citygusa.citygusatech.Dto;

import com.citygusa.citygusatech.Entity.HorasEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorasDto {

    private Long id;
    private String horas;

    public HorasDto(HorasEntity entity) {
        this.id = entity.getId();
        this.horas = entity.getHoras();
    }
}
