package com.citygusa.citygusatech.Api.Dto;

import com.citygusa.citygusatech.Services.Validation.UserInsertDtoValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "O campo nome não pode estar vazio")
    private String name;

}