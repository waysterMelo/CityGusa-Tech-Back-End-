package com.citygusa.com.citygusaapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    @Column(name = "indexB")
    private Double indexB;

    @Column(name = "indexF")
    private Double indexF;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "horas")
    private String horas;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDate.now();
        this.horas = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

}
