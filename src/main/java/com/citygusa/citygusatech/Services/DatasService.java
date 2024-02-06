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

    private void copyToEntity(DatasEntity entity, DatasDto dto){
        entity.setData(dto.getData());
        entity.getHoras().clear();
        for (HorasDto horasDto : dto.getHoras()){
             HorasEntity idHoras = horasRepository.getOne(horasDto.getId());
             entity.getHoras().add(idHoras);
        }
    }


    @Transactional
    public DatasDto insertDatas(DatasDto dto) {
        DatasEntity datasEntity = new DatasEntity();
        copyToEntity(datasEntity, dto);
        datasEntity = datasRepository.save(datasEntity);
        return new DatasDto(datasEntity);
    }
}
