package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.ControleOperacionalDto;
import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import com.citygusa.com.citygusaapi.Repository.ControleOperacionalRepository;
import com.citygusa.com.citygusaapi.Service.ControleOperacionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ControleOperacionalImpl implements ControleOperacionalService {

    private final ControleOperacionalRepository controleOperacionalRepository;

    @Autowired
    public ControleOperacionalImpl(ControleOperacionalRepository controleOperacionalRepository) {
        this.controleOperacionalRepository = controleOperacionalRepository;
    }


    private ControleOperacionalDto convertToDto(ControleOperacionalEntity rs) {
        return null;
    }

    @Override
    public Optional<ControleOperacionalDto> save(ControleOperacionalEntity entity) {
       ControleOperacionalEntity rs = controleOperacionalRepository.save(entity);
       ControleOperacionalDto dto = convertToDto(rs);
        return Optional.of(dto);
    }


}
