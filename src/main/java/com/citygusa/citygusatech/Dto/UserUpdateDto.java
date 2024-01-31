package com.citygusa.citygusatech.Dto;

import com.citygusa.citygusatech.Services.Validation.UserUpdateValid;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@UserUpdateValid
public class UserUpdateDto extends UserDto{


}