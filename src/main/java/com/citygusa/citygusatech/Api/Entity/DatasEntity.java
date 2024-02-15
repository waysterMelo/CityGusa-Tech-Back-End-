package com.citygusa.citygusatech.Api.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "datas")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data")
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private LocalDate data;
}
