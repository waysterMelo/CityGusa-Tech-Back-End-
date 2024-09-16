package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.AnaliseGusaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Repository.AnaliseGusaRepository;
import com.citygusa.com.citygusaapi.Service.AnaliseGusaService;
import jakarta.transaction.TransactionScoped;
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

    private AnaliseGusaDto convertToDto(AnaliseGusa entity){
        AnaliseGusaDto analiseGusaDto = new AnaliseGusaDto();
        analiseGusaDto.setId(entity.getId());
        analiseGusaDto.setFerro(entity.getFerro());
        analiseGusaDto.setProduto(entity.getProduto());
        analiseGusaDto.setEnxofre(entity.getEnxofre());
        analiseGusaDto.setSilicio(entity.getSilicio());
        analiseGusaDto.setCromo(entity.getCromo());
        analiseGusaDto.setFosforo(entity.getFosforo());
        analiseGusaDto.setManganes(entity.getManganes());
        analiseGusaDto.setTitanium(entity.getTitanium());
        analiseGusaDto.setCreatedAt(entity.getCreatedAt());
        return analiseGusaDto;
    }

    @Override
    @Transactional
    public ResponseEntity<AnaliseGusaDto> save(AnaliseGusa analiseGusa) {
        try {
            AnaliseGusa saved = analiseGusaRepository.save(analiseGusa);
            AnaliseGusaDto dto = convertToDto(saved);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AnaliseGusaDto> getAllAnalisesGusa(LocalDate createdAt) throws NoAnalisesFoundException {
        List<AnaliseGusa> analises = analiseGusaRepository.findAllByCreatedAt(createdAt);
        if (analises.isEmpty()) {
            throw new NoAnalisesFoundException("Não há análises para retornar na data informada: " + createdAt);
        }
        return analises.stream().map(AnaliseGusaDto::new).collect(Collectors.toList());
    }

}
