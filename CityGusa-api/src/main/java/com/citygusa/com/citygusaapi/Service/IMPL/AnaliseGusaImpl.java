package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.AnaliseGusaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import com.citygusa.com.citygusaapi.Repository.AnaliseGusaRepository;
import com.citygusa.com.citygusaapi.Service.AnaliseGusaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AnaliseGusaImpl implements AnaliseGusaService {

    @Autowired
    private AnaliseGusaRepository analiseGusaRepository;

    private AnaliseGusaDto convertToDto(AnaliseGusa entity){
        AnaliseGusaDto analiseGusaDto = new AnaliseGusaDto();
        analiseGusaDto.setId(entity.getId());
        analiseGusaDto.setData(entity.getData());
        analiseGusaDto.setFerro(entity.getFerro());
        analiseGusaDto.setAluminio(entity.getAluminio());
        analiseGusaDto.setSilicio(entity.getSilicio());
        return analiseGusaDto;
    }

    @Override
    public ResponseEntity<AnaliseGusaDto> save(AnaliseGusa analiseGusa) {
        try {
            AnaliseGusa saved = analiseGusaRepository.save(analiseGusa);
            AnaliseGusaDto dto = convertToDto(saved);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
