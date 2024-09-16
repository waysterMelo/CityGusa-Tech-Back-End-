package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.AnaliseMinerioDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseMineriosEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AnaliseMinerioService {

    ResponseEntity<AnaliseMinerioDto> save(AnaliseMineriosEntity entity);
    List<AnaliseMinerioDto> getAllAnalisesMinerios(LocalDate createdAt) throws NoAnalisesFoundException;
}
