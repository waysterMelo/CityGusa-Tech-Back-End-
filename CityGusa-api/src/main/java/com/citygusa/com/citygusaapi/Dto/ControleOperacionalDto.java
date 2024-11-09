package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;

import java.time.LocalDate;

public class ControleOperacionalDto {

    protected LocalDate createdAt;
    protected String horas;
    private Integer a;
    private Integer gaiola;
    private Integer cargaHora;
    private Integer cargaSeca;
    private Double vazao;
    private Double pressaoCoroa;
    private Double pressaoTopo;
    private Integer temperaturaCoroa;
    private Integer temperaturaTopo;
    private Integer acumuladoCarga;
    private Double mediaHoraCarga;
    private Integer ritmo;


    public ControleOperacionalDto(ControleOperacionalEntity entity) {
        this.createdAt = entity.getCreatedAt();
        this.horas = entity.getHoras();
        this.a = entity.getA();
        this.gaiola = entity.getGaiola();
        this.cargaHora = entity.getCargaHora();
        this.cargaSeca = entity.getCargaSeca();
        this.vazao = entity.getVazao();
        this.pressaoCoroa = entity.getPressaoCoroa();
        this.pressaoTopo = entity.getPressaoCoroa();
        this.temperaturaCoroa = entity.getTemperaturaCoroa();
        this.temperaturaTopo = entity.getTemperaturaTopo();
        this.acumuladoCarga = entity.getAcumuladoCarga();
        this.mediaHoraCarga = entity.getMediaHoraCarga();
        this.ritmo = entity.getRitmo();
    }
}
