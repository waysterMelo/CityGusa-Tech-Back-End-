package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Double sonda;
    private Integer densidadeKg;
    private Integer umidade;
    private Integer gusaKg;

    private Integer acumuladoCarga;
    private Double mediaHoraCarga;
    private Integer rt;


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
        this.sonda = entity.getSonda();
        this.densidadeKg = entity.getDensidadeKg();
        this.umidade = entity.getUmidade();
        this.gusaKg = entity.getGusaKg();

        this.acumuladoCarga = entity.getAcumuladoCarga();
        this.mediaHoraCarga = entity.getMediaHoraCarga();
        this.rt = entity.getRt();
    }
}
