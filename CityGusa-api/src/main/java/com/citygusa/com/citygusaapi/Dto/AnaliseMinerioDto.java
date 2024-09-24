package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.CadastrarAnaliseMineriosEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AnaliseMinerioDto {

    private Integer id;
    private String minerio;
    private String lote;
    private String patio;
    private Double tonelada;
    private Double ferro;
    private Double silica;
    private Double aluminio;
    private Double fosforo;
    private Double manganes;
    private Double ppc;
    private LocalDate createdAt;
    private Double fechamento;

    public AnaliseMinerioDto(CadastrarAnaliseMineriosEntity entity) {
        this.id = entity.getId();
        this.minerio = entity.getMinerio();
        this.lote = entity.getLote();
        this.patio = entity.getPatio();
        this.tonelada = entity.getTonelada();
        this.ferro = entity.getFerro();
        this.silica = entity.getSilica();
        this.aluminio = entity.getAluminio();
        this.fosforo = entity.getFosforo();
        this.manganes = entity.getManganes();
        this.ppc = entity.getPpc();
        this.fechamento = entity.getFechamento();
        this.createdAt = entity.getCreatedAt();
    }
}
