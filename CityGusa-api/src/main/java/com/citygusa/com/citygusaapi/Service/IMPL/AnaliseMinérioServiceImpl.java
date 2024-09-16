package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.AnaliseGusaDto;
import com.citygusa.com.citygusaapi.Dto.AnaliseMinerioDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseQuimicaMineriosEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Repository.AnaliseMinerioRepository;
import com.citygusa.com.citygusaapi.Service.AnaliseMinerioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnaliseMinérioServiceImpl implements AnaliseMinerioService {

    private final AnaliseMinerioRepository analiseMinerioRepository;

    @Autowired
    public AnaliseMinérioServiceImpl(AnaliseMinerioRepository analiseMinerioRepository) {
        this.analiseMinerioRepository = analiseMinerioRepository;
    }


    private AnaliseMinerioDto convertToDto(AnaliseQuimicaMineriosEntity entity){
        AnaliseMinerioDto dto = new AnaliseMinerioDto();
        dto.setId(entity.getId());
        dto.setMinerio(entity.getMinerio());
        dto.setLote(entity.getLote());
        dto.setTonelada(entity.getTonelada());
        dto.setFerro(entity.getFerro());
        dto.setSilica(entity.getSilica());
        dto.setAluminio(entity.getAluminio());
        dto.setFosforo(entity.getFosforo());
        dto.setManganes(entity.getManganes());
        dto.setPpc(entity.getPpc());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }



    @Override
    public ResponseEntity<AnaliseMinerioDto> save(AnaliseQuimicaMineriosEntity entity) {
        return null;
    }

    @Override
    public List<AnaliseMinerioDto> getAllAnalisesMinerios(LocalDate createdAt) throws NoAnalisesFoundException {
        return List.of();
    }
}
