package com.citygusa.citygusatech.Dto;

import com.citygusa.citygusatech.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;
    private String login;
    private String name;
    private String password;
    private Set<RoleDto> roles = new HashSet<>();

    public UserDto(Users entity) {
        id = entity.getId();
        login = entity.getLogin();
        name = entity.getName();
        password = entity.getPassword();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDto(role)));
    }
}