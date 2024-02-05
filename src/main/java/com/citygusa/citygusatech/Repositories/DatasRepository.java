package com.citygusa.citygusatech.Repositories;

import com.citygusa.citygusatech.Entity.DatasEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface DatasRepository extends CrudRepository<DatasEntity, Long> {
}
