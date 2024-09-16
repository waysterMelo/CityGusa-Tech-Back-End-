package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.AnaliseMinerioDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseMineriosEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Repository.AnaliseMinerioRepository;
import com.citygusa.com.citygusaapi.Service.AnaliseMinerioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AnaliseMinerioServiceImpl implements AnaliseMinerioService {

    private final AnaliseMinerioRepository analiseMinerioRepository;

    @Autowired
    public AnaliseMinerioServiceImpl(AnaliseMinerioRepository analiseMinerioRepository) {
        this.analiseMinerioRepository = analiseMinerioRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<AnaliseMinerioDto> save(AnaliseMineriosEntity entity) {
        try {
            AnaliseMineriosEntity saved = analiseMinerioRepository.save(entity);
            AnaliseMinerioDto dto = new AnaliseMinerioDto(saved);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AnaliseMinerioDto> getAllAnalisesMinerios(LocalDate createdAt) throws NoAnalisesFoundException {
        return List.of(); // Método a ser implementado
    }
}
