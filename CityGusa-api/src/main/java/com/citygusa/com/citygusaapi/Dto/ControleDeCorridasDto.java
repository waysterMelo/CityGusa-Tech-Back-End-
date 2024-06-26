package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControleDeCorridasDto {

    private Long id;
    private LocalDate data = LocalDate.now();
    private Integer cacambas;
    private LocalTime horaAbertura;
    private LocalTime horaTampa;
    private Double temperatura;
    private Double reducao;
    private Double reservaFundida;
    private String escoriaVisual;
    private Double producao;
    private Double producaoAcumulada;
    private Double media;
    private Double cecDiaM3;
    private Double cecDiaKg;

    public ControleDeCorridasDto(ControleCorridas entity){
        this.id = entity.getId();
        this.data = entity.getData();
        this.cacambas = entity.getCacambas();
        this.horaAbertura = entity.getHoraAbertura();
        this.horaTampa = entity.getHoraTampa();
        this.temperatura = entity.getTemperatura();
        this.reducao = entity.getReducao();
        this.reservaFundida = entity.getReservaFundida();
        this.escoriaVisual = entity.getEscoriaVisual();
        this.producao = entity.getProducao();
        this.producaoAcumulada = entity.getProducaoAcumulada();
        this.media = entity.getMedia();
        this.cecDiaM3 = entity.getCecDiaM3();
        this.cecDiaKg = entity.getCecDiaKg();
    }

}
