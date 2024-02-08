package com.citygusa.citygusatech.Dto;

import com.citygusa.citygusatech.Entity.DatasEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
