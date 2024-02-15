package com.citygusa.citygusatech.Services.Validation;


import com.citygusa.citygusatech.Api.Dto.UserUpdateDto;
import com.citygusa.citygusatech.Api.Entity.Users;
import com.citygusa.citygusatech.Repositories.UserRepository;
import com.citygusa.citygusatech.Resources.exceptions.FieldMessagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidation implements ConstraintValidator<UserUpdateValid, UserUpdateDto> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;


    @Override
    public void initialize(UserUpdateValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserUpdateDto dto, ConstraintValidatorContext context) {
        List<FieldMessagens> fieldMessagens = new ArrayList<>();

        //identifica um atributo específico armazenado no objeto de requisição.
        var uriID = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriID.get("id"));
        //achar user por email
        Users user =  userRepository.findByEmail(dto.getEmail());

        //A condição completa verifica se existe uma incompatibilidade entre o userId
        // fornecido e o ID do objeto user recuperado
        if (user != null && userId != user.getId()) {
            fieldMessagens.add(new FieldMessagens("email", "Email já Cadastrado"));
        }

        for (FieldMessagens f : fieldMessagens){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(f.getMessage())
                    .addPropertyNode(f.getFieldMessage()).addConstraintViolation();
        }
        return fieldMessagens.isEmpty();
    }
}
