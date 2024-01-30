package com.citygusa.citygusatech.Dto;

import com.citygusa.citygusatech.Entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String role;

    public RoleDto(Roles roles) {
        id = roles.getId();
        role = roles.getRole();
    }

}