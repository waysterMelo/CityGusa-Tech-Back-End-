package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "controle_de_corridas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControleCorridas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "cacambas", nullable = false)
    private Integer cacambas;

    @Column(name = "hora_abertura", nullable = false)
    private LocalTime horaAbertura;

    @Column(name = "hora_tampa", nullable = false)
    private LocalTime horaTampa;

    @Column(name = "temperatura", nullable = false)
    private Double temperatura;

    @Column(name = "reducao", nullable = false)
    private Double reducao;

    @Column(name = "reserva_fundida", nullable = false)
    private Double reservaFundida;

    @Column(name = "escoria_visual", nullable = false)
    private String escoriaVisual;

    @Column(name = "producao", nullable = false)
    private Double producao;

    @Column(name = "producao_acumulada", nullable = false)
    private Double producaoAcumulada;

    @Column(name = "media", nullable = false)
    private Double media;

    @Column(name = "cec_dia_m3", nullable = false)
    private Double cecDiaM3;

    @Column(name = "cec_dia_kg", nullable = false)
    private Double cecDiaKg;
}
