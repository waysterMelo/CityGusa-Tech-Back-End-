package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "analise_escoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "magnesio ")
    private Double magnesio ;

    @Column(name = "ferro")
    private Double ferro;

    @Column(name = "manganes")
    private Double manganes;

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
