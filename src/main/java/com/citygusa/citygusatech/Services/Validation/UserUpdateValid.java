package com.citygusa.citygusatech.Services.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = UserUpdateValidation.class)

@Retention(RetentionPolicy.RUNTIME)
public @interface UserUpdateValid {

    String message() default "VALIDATION ERROR";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
