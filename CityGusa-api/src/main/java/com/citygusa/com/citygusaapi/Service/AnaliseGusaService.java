package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.AnaliseGusaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AnaliseGusaService{

    ResponseEntity<AnaliseGusaDto> save(AnaliseGusa analiseGusa);

    List<AnaliseGusaDto> getAllCorridasToday(LocalDate createdAt) throws NoAnalisesFoundException;
}
