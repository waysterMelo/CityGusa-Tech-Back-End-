package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AnaliseGusaDto {

    private Integer id;
    private String produto;
    private String ferro;
    private String silicio;
    private String aluminio;

    public AnaliseGusaDto(AnaliseGusa entity) {
        this.id = entity.getId();
        this.produto = entity.getProduto();
        this.silicio = entity.getSilicio();
        this.ferro = entity.getFerro();
        this.aluminio = entity.getAluminio();
    }
}
