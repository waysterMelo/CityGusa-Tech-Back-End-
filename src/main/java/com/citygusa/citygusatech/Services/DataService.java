package com.citygusa.citygusatech.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.citygusa.citygusatech.Api.Dto.DatasDto;
import com.citygusa.citygusatech.Api.Entity.DatasEntity;
import com.citygusa.citygusatech.Repositories.DatasRepository;
import com.citygusa.citygusatech.Services.ExceptionsService.DataAlreadyExistsException;

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
