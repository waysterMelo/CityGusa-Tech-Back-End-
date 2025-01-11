package com.citygusa.com.citygusaapi.Service.Impl;

import com.citygusa.com.citygusaapi.Dto.AnaliseGusaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusaEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Repository.AnaliseGusaRepository;
import com.citygusa.com.citygusaapi.Service.AnaliseGusaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnaliseGusaImpl implements AnaliseGusaService {


    private final AnaliseGusaRepository analiseGusaRepository;

    @Autowired
    public AnaliseGusaImpl(AnaliseGusaRepository analiseGusaRepository) {
        this.analiseGusaRepository = analiseGusaRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<AnaliseGusaDto> save(AnaliseGusaEntity analiseGusa) {
        try {
            AnaliseGusaEntity saved = analiseGusaRepository.save(analiseGusa);
            AnaliseGusaDto dto = new AnaliseGusaDto(saved);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public List<AnaliseGusaDto> getAllAnalisesGusa(LocalDate createdAt) throws NoAnalisesFoundException {
        List<AnaliseGusaEntity> analises = analiseGusaRepository.findAllByCreatedAt(createdAt);
        if (analises.isEmpty()) {
            throw new NoAnalisesFoundException("Não há análises para retornar na data informada: " + createdAt);
        }
        return analises.stream().map(AnaliseGusaDto::new).collect(Collectors.toList());
    }

}
