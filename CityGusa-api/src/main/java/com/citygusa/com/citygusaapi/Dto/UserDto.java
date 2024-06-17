package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.UserEntity;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserDto extends UserEntity {

    Integer id;
    String nome;
    String senha;
    LocalDate dateRegistration = LocalDate.now();

    public UserDto(UserEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.senha = entity.getSenha();
        this.dateRegistration = entity.getDateRegistration();
    }


}
