package com.citygusa.citygusatech.Services.Validation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserInsertValidation.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInsertDtoValid {

    String message() default "Erro de Validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
