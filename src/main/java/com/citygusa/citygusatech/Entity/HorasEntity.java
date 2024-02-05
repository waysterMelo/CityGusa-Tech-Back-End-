package com.citygusa.citygusatech.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "horas_do_turno")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "horas")
    private String horas;

    @ManyToMany(mappedBy = "horas")
    Set<DatasEntity> data = new HashSet<DatasEntity>();
}
