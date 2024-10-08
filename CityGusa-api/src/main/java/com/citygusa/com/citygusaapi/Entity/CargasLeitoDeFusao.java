package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "cargas_leito_de_fusao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargasLeitoDeFusao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_atual", nullable = false)
    private LocalDate createdAt;
    private String horas;
    private Integer numeroDaCarga;
    private Double porcentagem;
    private String tipoMinerio;
    private Double quantidade;
    private Integer calcareo;
    private Integer bauxita;
    private Integer coque;
    private Integer secas;
    private Integer totalCargas;
    private Integer sucataGusa;
    private Integer sucataAco;

}
