package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends UserEntity {

    Integer id;
    String name;
    String password;
    LocalDate dateRegistration = LocalDate.now();

    public UserDto(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.password = entity.getPassword();
        this.dateRegistration = entity.getDateRegistration();
    }


}
