package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "controle_de_corridas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControleCorridas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String vazamento_inicio;

    @Column(unique = true, nullable = true)
    private String vazamento_fim;

    @Column(unique = true, nullable = false)
    private Integer minutos;

    @Column(unique = true, nullable = true)
    private Integer conchas;

    @Column(unique = true, nullable = true)
    private Integer silicio_visual;

    @Column(unique = true, nullable = true)
    private Integer silicio_real;

    @Column(unique = true, nullable = true)
    private String fosforo;

    @Column(unique = true, nullable = true)
    private String manganes;

    @Column(unique = true, nullable = true)
    private String silica;

    @Column(unique = true, nullable = true)
    private String escoria_inicio;

    @Column(unique = true, nullable = true)
    private String escoria_fim;

    @Column(unique = true, nullable = true)
    private String tipo_escoria;

    @Column(unique = true, nullable = false)
    private Integer carga_fundida_de;

    @Column(unique = true, nullable = false)
    private Integer carga_fundida_ate;

    @Column(unique = true, nullable = false)
    private Integer quantidade;

    @Column(unique = true, nullable = true)
    private Integer fe_gusa_kg;

    @Column(precision = 10, scale = 2, nullable = true)
    private BigDecimal ferro;

    @Column(precision = 10, scale = 2, nullable = true)
    private BigDecimal realTn;

    @Column(unique = true, nullable = true)
    private BigDecimal tempo_corrida;


    @Column(precision = 10, scale = 3, nullable = true)
    private BigDecimal gusa_minuto;

    @Column(unique = true, nullable = false)
    private Integer carvao_kg;

    @Column(unique = true, nullable = false)
    private Integer carvao_metros;

    @Column(precision = 10, scale = 2, nullable = true)
    private BigDecimal mt;

    @Column(unique = true, nullable = true)
    private Integer sopradores_1;

    @Column(unique = true, nullable = true)
    private Integer sopradores_2;

    @Column(unique = true, nullable = true)
    private Integer sopradores_3;

    @Column(unique = true, nullable = true)
    private Integer sopradores_4;

    @Column(unique = true, nullable = true)
    private Integer sopradores_5;

    @Column
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}