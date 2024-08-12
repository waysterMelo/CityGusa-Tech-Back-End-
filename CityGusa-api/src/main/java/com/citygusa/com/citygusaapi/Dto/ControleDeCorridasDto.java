package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControleDeCorridasDto {

    private Long id;
    private String horaInicio;
    private String horaFim;
    private Integer minutos;
    private Integer minutosAcumulados;
    private Integer conchas;
    private Double silicioVisual;
    private Double silicioReal;
    private Double fosforo;
    private Double mediaFosforo;
    private Double manganes;
    private Double mediaManganes;
    private Double silica;
    private Double mediaSilica;
    private String escoriaInicio;
    private String escoriaFim;
    private String tipoEscoria;
    private Integer cargaFundidaDe;
    private Integer cargaFundidaAte;
    private Integer quantidade;
    private Integer feGusaKg;
    private BigDecimal ferro;
    private BigDecimal realTn;
    private Integer tempoCorridaMinutos;
    private BigDecimal gusaMinuto;
    private Integer carvaoKg;
    private BigDecimal carvaoMetros;
    private Integer sopradores1;
    private Integer sopradores2;
    private Integer sopradores3;
    private Integer sopradores4;
    private Integer sopradores5;
    private BigInteger temperatura;
    private LocalDate createdAt;

    public ControleDeCorridasDto(ControleCorridas entity,
               Integer minutosAcumulados, Double mediaFosforo,
               Double mediaSilica, Double mediaManganes) {
        this.id = entity.getId();
        this.horaInicio = entity.getHoraInicio();
        this.horaFim = entity.getHoraFim();
        this.minutos = entity.getMinutos();
        this.minutosAcumulados = minutosAcumulados;
        this.conchas = entity.getConchas();
        this.silicioVisual = entity.getSilicioVisual();
        this.silicioReal = entity.getSilicioReal();
        this.fosforo = entity.getFosforo();
        this.manganes = entity.getManganes();
        this.silica = entity.getSilica();
        this.escoriaInicio = entity.getEscoriaInicio();
        this.escoriaFim = entity.getEscoriaFim();
        this.tipoEscoria = entity.getTipoEscoria();
        this.cargaFundidaDe = entity.getCargaFundidaDe();
        this.cargaFundidaAte = entity.getCargaFundidaDe();
        this.quantidade = entity.getQuantidade();
        this.feGusaKg = entity.getFeGusaKg();
        this.ferro = entity.getFerro();
        this.realTn = entity.getRealTn();
        this.tempoCorridaMinutos = entity.getTempoCorridaMinutos();
        this.gusaMinuto = entity.getGusaMinuto();
        this.carvaoKg = entity.getCarvaoKg();
        this.carvaoMetros = entity.getCarvaoMetros();
        this.sopradores1 = entity.getSopradores1();
        this.sopradores2 = entity.getSopradores2();
        this.sopradores3 = entity.getSopradores3();
        this.sopradores4 = entity.getSopradores4();
        this.sopradores5 = entity.getSopradores5();
        this.temperatura = entity.getTemperatura();
        this.createdAt = entity.getCreatedAt();
        this.mediaFosforo = mediaFosforo;
        this.mediaSilica = mediaSilica;
        this.mediaManganes = mediaManganes;
    }
}
