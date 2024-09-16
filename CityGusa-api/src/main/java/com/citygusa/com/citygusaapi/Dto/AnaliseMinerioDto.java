package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import com.citygusa.com.citygusaapi.Entity.AnaliseQuimicaMineriosEntity;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AnaliseMinerioDto {

    private Integer id;
    private String minerio;
    private String lote;
    private Double tonelada;
    private Double ferro;
    private String silica;
    private Double aluminio;
    private Double fosforo;
    private Double manganes;
    private Double ppc;
    private LocalDate createdAt;

    public AnaliseMinerioDto(AnaliseQuimicaMineriosEntity entity) {
        this.id = entity.getId();
        this.minerio = entity.getMinerio();
        this.lote = entity.getLote();
        this.tonelada = entity.getTonelada();
        this.ferro = entity.getFerro();
        this.silica = entity.getSilica();
        this.aluminio = entity.getAluminio();
        this.fosforo = entity.getFosforo();
        this.manganes = entity.getManganes();
        this.ppc = entity.getPpc();
        this.createdAt = entity.getCreatedAt();
    }
}
