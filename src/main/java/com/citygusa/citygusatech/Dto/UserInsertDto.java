package com.citygusa.citygusatech.Dto;

import com.citygusa.citygusatech.Services.Validation.UserInsertDtoValid;
import jakarta.validation.constraints.*;
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
@UserInsertDtoValid
public class UserInsertDto extends UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Min(3)
    private String password;
    @Email
    private String email;
    @NotEmpty(message = "Field name must not be empty")
    private String name;

}