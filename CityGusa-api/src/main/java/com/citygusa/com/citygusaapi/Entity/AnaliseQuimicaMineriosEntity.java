package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseQuimicaMineriosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String minerio;
    @Column
    private String lote;
    @Column
    private Double tonelada;
    @Column
    private Double ferro;
    @Column
    private String silica;
    @Column
    private Double aluminio;
    @Column
    private Double fosforo;
    @Column
    private Double manganes;
    @Column
    private Double ppc;
    @Column
    private Double fechamento;
    @Column(nullable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
}
