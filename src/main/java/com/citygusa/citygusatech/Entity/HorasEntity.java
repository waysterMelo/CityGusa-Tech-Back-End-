package com.citygusa.citygusatech.Entity;

import com.citygusa.citygusatech.Dto.HorasDto;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Table(name = "horas_do_turno")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorasEntity extends HorasDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "horas")
    private String horas;

}
