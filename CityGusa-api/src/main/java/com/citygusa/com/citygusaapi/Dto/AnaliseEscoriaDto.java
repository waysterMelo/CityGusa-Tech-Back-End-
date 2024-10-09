package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.AnaliseEscoriaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnaliseEscoriaDto {

    private Long id;
    private Double calcio;
    private Double silicio;
    private Double aluminio;
    private Double magnesio;
    private Double ferro;
    private Double manganes;
    private Double indexB;
    private Double indexF;
    private LocalDate createdAt;
    private String horas;

    public AnaliseEscoriaDto(AnaliseEscoriaEntity entity) {
        this.id = entity.getId();
        this.calcio = entity.getCalcio();
        this.silicio = entity.getSilicio();
        this.aluminio = entity.getAluminio();
        this.magnesio = entity.getMagnesio();
        this.ferro = entity.getFerro();
        this.manganes = entity.getManganes();
        this.indexB = entity.getIndexB();
        this.indexF = entity.getIndexF();
        this.createdAt = entity.getCreatedAt();
        this.horas = entity.getHoras();
    }


}
