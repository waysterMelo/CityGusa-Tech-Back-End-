package com.citygusa.citygusatech.Api.Dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.citygusa.citygusatech.Api.Entity.Roles;
import com.citygusa.citygusatech.Api.Entity.Users;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    private Long id;
    private String email;
    private String name;
    private String password;
    private Set<RoleDto> roles = new HashSet<>();

    public UserDto(Users entity) {
        id = entity.getId();
        email = entity.getEmail();
        name = entity.getName();
        password = entity.getPassword();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDto(role)));
    }

   
}