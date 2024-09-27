package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.AnaliseGusaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusaEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AnaliseGusaService{

    ResponseEntity<AnaliseGusaDto> save(AnaliseGusaEntity analiseGusa);
    List<AnaliseGusaDto> getAllAnalisesGusa(LocalDate createdAt) throws NoAnalisesFoundException;
}
