package com.citygusa.citygusatech.Services;

import com.citygusa.citygusatech.Dto.DatasDto;
import com.citygusa.citygusatech.Dto.HorasDto;
import com.citygusa.citygusatech.Entity.DatasEntity;
import com.citygusa.citygusatech.Entity.HorasEntity;
import com.citygusa.citygusatech.Repositories.DatasRepository;
import com.citygusa.citygusatech.Repositories.HorasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DatasService {

    @Autowired
    DatasRepository datasRepository;
    @Autowired
    HorasRepository horasRepository;

    @Transactional
    public DatasDto insertDatas(DatasDto datasDto){
        DatasEntity datasEntity = new DatasEntity();
        datasDto.setData(datasEntity.getData());
        datasEntity.getHoras().clear();
        for (HorasDto horasDto : datasDto.getHoras()){
             HorasEntity idHoras = horasRepository.getOne(horasDto.getId());
             datasEntity.getHoras().add(idHoras);
        }
        datasEntity = datasRepository.save(datasEntity);
        return new DatasDto(datasEntity);
    }

}
