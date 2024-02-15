package com.citygusa.citygusatech.Api.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Table(name = "controle_forno")
@NoArgsConstructor
@AllArgsConstructor
public class FornoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    @Column(name = "dia_mes_ano")
//    private int data;
//
//    private int horas;
    private String gaiola;
    @Column(name = "a_mudar_depois")
    private String aMudarDepois;
    @Column(name = "carga_hora_seca")
    private int carga;
    @Column(name = "acumulo_ch_seca")
    private int acumuloSeco;
    @Column(name = "carga_hora")
    private int cargaHhora;
    @Column(name = "acumulo")
    private int acumuloCarga;
    @Column(name = "media_hora")
    private int mediaHora;
    @Column(name = "rt")
    private int rt;
//  private String turno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "data_id")
    private DatasEntity data;


}
