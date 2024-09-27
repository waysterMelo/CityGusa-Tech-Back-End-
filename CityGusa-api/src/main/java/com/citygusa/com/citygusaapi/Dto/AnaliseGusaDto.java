package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.AnaliseGusaEntity;
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
    private String enxofre;
    private String manganes;
    private String cromo;
    private String fosforo;
    private String titanium;
    private LocalDate createdAt;

    public AnaliseGusaDto(AnaliseGusaEntity entity) {
        this.id = entity.getId();
        this.produto = entity.getProduto();
        this.silicio = entity.getSilicio();
        this.ferro = entity.getFerro();
        this.enxofre = entity.getEnxofre();
        this.manganes = entity.getManganes();
        this.cromo = entity.getCromo();
        this.fosforo = entity.getFosforo();
        this.titanium = entity.getTitanium();
        this.createdAt = entity.getCreatedAt();
    }
}
