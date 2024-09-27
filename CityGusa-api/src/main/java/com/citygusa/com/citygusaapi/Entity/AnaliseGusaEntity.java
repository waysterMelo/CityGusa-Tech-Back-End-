package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "analise_gusa")
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseGusaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "produto")
    private String produto;

    @Column(name = "ferro")
    private String ferro;

    @Column(name = "silicio")
    private String silicio;

    @Column(name = "enxofre")
    private String enxofre;

    @Column(name = "manganes")
    private String manganes;

    @Column(name = "cromo")
    private String cromo;

    @Column(name = "fosforo")
    private String fosforo;

    @Column(name = "titanium")
    private String titanium;

    @Column(nullable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
}
