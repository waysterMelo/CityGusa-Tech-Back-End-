package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.AnaliseGusaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface AnaliseGusaService{

    ResponseEntity<AnaliseGusaDto> save(AnaliseGusa analiseGusa);

}
