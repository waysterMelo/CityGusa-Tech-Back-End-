package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.ControleOperacionalDto;
import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ControleOperacionalService {

    Optional<ControleOperacionalDto> save(ControleOperacionalEntity entity);


}
