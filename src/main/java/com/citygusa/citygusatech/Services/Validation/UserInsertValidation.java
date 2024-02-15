package com.citygusa.citygusatech.Services.Validation;

import com.citygusa.citygusatech.Api.Dto.UserInsertDto;
import com.citygusa.citygusatech.Api.Entity.Users;
import com.citygusa.citygusatech.Repositories.UserRepository;
import com.citygusa.citygusatech.Resources.exceptions.FieldMessagens;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidation implements ConstraintValidator<UserInsertDtoValid, UserInsertDto> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void initialize(UserInsertDtoValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserInsertDto dto, ConstraintValidatorContext context) {
        List<FieldMessagens> lista = new ArrayList<>();

           Users usersList = userRepository.findByEmail(dto.getEmail());
           if(usersList != null){
               lista.add(new FieldMessagens("email", "Email já cadastrado"));
           }

           for (FieldMessagens f : lista){
               //This line disables the default mechanism for generating constraint violation messages.
                   context.disableDefaultConstraintViolation();
                   context.buildConstraintViolationWithTemplate(f.getMessage()).addPropertyNode(f.getFieldMessage())
                           .addConstraintViolation();
           }
           return lista.isEmpty();
    }
}
