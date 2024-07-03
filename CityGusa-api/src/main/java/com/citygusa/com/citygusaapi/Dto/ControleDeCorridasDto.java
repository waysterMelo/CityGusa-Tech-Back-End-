package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControleDeCorridasDto {

    private Long id;
    private String data;
    private Integer cacambas;
    private String horaAbertura;
    private String horaTampa;
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter horaFormatted = DateTimeFormatter.ofPattern("HH:mm");
        this.id = entity.getId();
        this.data = entity.getData().format(dateTimeFormatter);
        this.cacambas = entity.getCacambas();
        this.horaAbertura = entity.getHoraAbertura().format(horaFormatted);
        this.horaTampa = entity.getHoraTampa().format(horaFormatted);
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
