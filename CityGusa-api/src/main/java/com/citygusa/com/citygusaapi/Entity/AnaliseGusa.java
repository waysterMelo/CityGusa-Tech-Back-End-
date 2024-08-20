package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "analise_gusa")
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseGusa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_do_dia")
    private String data;

    @Column(name = "produto")
    private String produto;

    @Column(name = "ferro")
    private String ferro;

    @Column(name = "silicio")
    private String silicio;

    @Column(name = "aluminio")
    private String aluminio;

}
