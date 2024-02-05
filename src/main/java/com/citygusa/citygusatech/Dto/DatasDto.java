package com.citygusa.citygusatech.Dto;

import com.citygusa.citygusatech.Entity.DatasEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DatasDto implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String data;
    private Set<HorasDto> horas = new HashSet<>();

    public DatasDto(DatasEntity entity) {
        this.id = entity.getId();
        this.data = entity.getData();
        entity.getHoras().forEach(horas -> this.horas.add(new HorasDto()));
    }
}
