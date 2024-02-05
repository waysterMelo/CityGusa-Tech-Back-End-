package com.citygusa.citygusatech.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "datas")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data")
    private String data;

    @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable(name = "horas_datas",
                    joinColumns = @JoinColumn(name = "datas_id"),
                    inverseJoinColumns = @JoinColumn(name = "horas_id"))
    public Set<HorasEntity> horas = new HashSet<>();

}
