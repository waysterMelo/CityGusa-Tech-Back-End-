package com.citygusa.com.citygusaapi.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "controle_operacional")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControleOperacionalEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate createdAt;
    private String horas;
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
    private Double acumuladoKilos;

    private Integer acumuladoCarga;
    private Integer acumuladoCargaSeca;
    private BigDecimal mediaHoraCarga;
    private BigDecimal rt;
    private BigInteger fatorBaseDensidadeSeca;


    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDate.now();
        this.horas = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }



}
