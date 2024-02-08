package com.citygusa.citygusatech.Services;

import com.citygusa.citygusatech.Dto.DatasDto;
import com.citygusa.citygusatech.Entity.DataEntity;
import com.citygusa.citygusatech.Entity.DatasEntity;
import com.citygusa.citygusatech.Repositories.DatasRepository;
import com.citygusa.citygusatech.Services.ExceptionsService.DataAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class DataService {

    @Autowired
    private DatasRepository datasRepository;

    @Transactional
    public DatasDto save(DatasDto dto){

        // Verifica se a data já existe no banco de dados
        if (datasRepository.existsByData(dto.getData())) {
            // Se a data já existe, você pode lidar com isso de acordo com sua lógica
            throw new DataAlreadyExistsException("A data já existe no banco de dados");
        }


        DatasEntity entity = new DatasEntity();
        entity.setId(dto.getId());
        entity.setData(dto.getData());

        datasRepository.save(entity);
        return new DatasDto(entity);
    }

}
