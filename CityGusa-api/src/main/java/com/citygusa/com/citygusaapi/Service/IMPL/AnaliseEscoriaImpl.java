package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.AnaliseEscoriaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseEscoriaEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Repository.AnaliseEscoriaRepository;
import com.citygusa.com.citygusaapi.Service.AnaliseEscoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnaliseEscoriaImpl implements AnaliseEscoriaService {


    private final AnaliseEscoriaRepository analiseEscoriaRepository;

    @Autowired
    public AnaliseEscoriaImpl(AnaliseEscoriaRepository analiseEscoriaRepository) {
        this.analiseEscoriaRepository = analiseEscoriaRepository;
    }

    @Override
    public ResponseEntity<AnaliseEscoriaDto> save(AnaliseEscoriaEntity entity) {
        AnaliseEscoriaEntity saved =  analiseEscoriaRepository.save(entity);
        AnaliseEscoriaDto dto = new AnaliseEscoriaDto(saved);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public List<AnaliseEscoriaDto> getAllAnalisesEscoria(LocalDate createdAt) throws NoAnalisesFoundException {
        List<AnaliseEscoriaEntity> lista_analises = analiseEscoriaRepository.findAllByCreatedAt(createdAt);
        if (lista_analises.isEmpty()) {
            throw new NoAnalisesFoundException("Não há análises para retornar na data informada: " + createdAt);
        }
        return lista_analises.stream().map(AnaliseEscoriaDto::new).collect(Collectors.toList());
    }

}
