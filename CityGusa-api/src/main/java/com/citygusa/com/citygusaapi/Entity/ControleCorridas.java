package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "controle_de_corridas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControleCorridas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String horaInicio;
    private String horaFim;
    private Integer minutos;
    private Integer conchas;
    private Double silicioVisual;
    private Double silicioReal;
    private Double fosforo;
    private Double manganes;
    private Double silica;
    private String escoriaInicio;
    private String escoriaFim;
    private String tipoEscoria;
    private Integer cargaFundidaDe;
    private Integer cargaFundidaAte;
    private Integer quantidade;
    private Integer feGusaKg;
    @Column(precision = 10, scale = 2)
    private BigDecimal ferro;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal realTn;
    private Double ritmo;
    private Integer tempoCorridaMinutos;
    @Column(precision = 38, scale = 3)
    private BigDecimal gusaMinuto;
    private Integer carvaoKg;
    private BigDecimal carvaoMetros;
    private Integer sopradores1;
    private Integer sopradores2;
    private Integer sopradores3;
    private Integer sopradores4;
    private Integer sopradores5;
    private BigInteger temperatura;

    @Column(nullable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
}
