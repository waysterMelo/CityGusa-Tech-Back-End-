package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "cadastro_minerios")
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarMineriosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String minerio;
    @Column
    private Double valorTonelada;
    @Column
    private String lote;
    @Column
    private String patio;
    @Column
    private String transportador;
    @Column
    private Double frete;
    @Column(nullable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
}