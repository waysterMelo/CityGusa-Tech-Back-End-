package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {


    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String nome;
    @Column
    String senha;
    @Column(name = "date_registry")
    LocalDate dateRegistration;


}
