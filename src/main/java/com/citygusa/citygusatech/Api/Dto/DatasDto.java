package com.citygusa.citygusatech.Api.Dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import com.citygusa.citygusatech.Api.Entity.DatasEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatasDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate data = LocalDate.now();

    public DatasDto(DatasEntity entity) {
        this.id = entity.getId();
        this.data = entity.getData();
    }
}
