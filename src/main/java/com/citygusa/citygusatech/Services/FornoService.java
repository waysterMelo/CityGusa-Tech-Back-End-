package com.citygusa.citygusatech.Services;

import com.citygusa.citygusatech.Api.Dto.FornoDto;
import com.citygusa.citygusatech.Api.Entity.FornoEntity;
import com.citygusa.citygusatech.Repositories.FornoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FornoService {

    private FornoRepository fornoRepository;

    @Autowired
    public FornoService(FornoRepository fornoRepository) {
        this.fornoRepository = fornoRepository;
    }

    @Transactional
    public FornoDto save(FornoDto fornoDto){
        FornoEntity fornoEntity = new FornoEntity();
        fornoEntity.setGaiola(fornoDto.getGaiola());
        fornoEntity.setAMudarDepois(fornoDto.getAMudarDepois());
        fornoEntity.setCarga(fornoDto.getCarga());
        fornoEntity.setAcumuloSeco(fornoDto.getAcumuloSeco());
        fornoEntity.setCargaHhora(fornoDto.getCargaHhora());
        fornoEntity.setAcumuloCarga(fornoDto.getAcumuloCarga());
        fornoEntity.setMediaHora(fornoDto.getMediaHora());
        fornoEntity.setRt(fornoDto.getRt());

        //fornoEntity.getData().clear();

        fornoEntity = fornoRepository.save(fornoEntity);
        return null;
    }
}
