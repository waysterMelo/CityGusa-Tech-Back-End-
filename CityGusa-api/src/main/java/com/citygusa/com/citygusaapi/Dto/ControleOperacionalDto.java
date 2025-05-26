package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControleOperacionalDto {

    private Integer id;
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
    private Double densidadeMedia;
    private Integer umidade;
    private BigDecimal umidadeMedia;
    private Integer gusaKg;
    private BigDecimal acumuladoKilos;
    private Integer acumuladoCarga;
    private Integer acumuladoCargaSeca;
    private BigDecimal mediaHoraCarga;
    private BigDecimal rt;
    private @NotNull(message = "Fator Base Densidade Seca não pode ser nulo.")
    @Positive(message = "Fator Base Densidade Seca deve ser positivo.")
    @Digits(integer = 5, fraction = 4, message = "Fator Base Densidade Seca inválido.") BigDecimal fatorBaseDensidadeSeca;
    private BigDecimal pesoCarvaoCalc;
    private BigDecimal carvaoEnfornado;
    private BigInteger carvaoEnfornadoMedia;
    private BigDecimal consumoKg;
    private BigDecimal consumoMetros;
    private BigDecimal positivoNegativo;


    public ControleOperacionalDto(ControleOperacionalEntity entity) {
        this.id = entity.getId();
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
        this.densidadeMedia = entity.getDensidadeMedia();
        this.umidade = entity.getUmidade();
        this.umidadeMedia = entity.getUmidadeMedia();
        this.gusaKg = entity.getGusaKg();
        this.acumuladoKilos = entity.getAcumuladoKilos();
        this.acumuladoCarga = entity.getAcumuladoCarga();
        this.acumuladoCargaSeca = entity.getAcumuladoCargaSeca();
        this.mediaHoraCarga = entity.getMediaHoraCarga();
        this.rt = entity.getRt();
        this.fatorBaseDensidadeSeca = entity.getFatorBaseDensidadeSeca();
        this.pesoCarvaoCalc = entity.getPesoCarvaoCalc();
        this.carvaoEnfornado = entity.getCarvaoEnfornado();
        this.carvaoEnfornadoMedia = entity.getCarvaoEnfornadoMedia();
        this.consumoKg = entity.getConsumoKg();
        this.consumoMetros = entity.getConsumoMetros();
        this.positivoNegativo = entity.getPositivoNegativo();

    }
}
