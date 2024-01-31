package com.citygusa.citygusatech.Dto;

import com.citygusa.citygusatech.Entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto implements Serializable {
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