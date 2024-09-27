package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "analise_escoria")
@Data
@AllArgsConstructor
public class AnaliseEscoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "calcio")
    private Double calcio;

    @Column(name = "silicio")
    private Double silicio;

    @Column(name = "aluminio")
    private Double aluminio;

    @Column(name = "magnésio ")
    private Double magnésio ;

    @Column(name = "ferro")
    private Double ferro;

    @Column(name = "manganês")
    private Double manganês;

    @Column(name = "kilogram_per_ton")
    private Double kgPerT;

    @Column(name = "basic_index")
    private Double ib;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDate.now();
    }

}
