package com.citygusa.citygusatech.Api.Entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "datas")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String data;
}
