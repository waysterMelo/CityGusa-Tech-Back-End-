package com.citygusa.citygusatech.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertDto extends UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String password;

}