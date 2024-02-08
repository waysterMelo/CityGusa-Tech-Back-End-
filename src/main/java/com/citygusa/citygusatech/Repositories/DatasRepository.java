package com.citygusa.citygusatech.Repositories;

import com.citygusa.citygusatech.Entity.DatasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DatasRepository extends JpaRepository<DatasEntity, Long> {

    boolean existsByData(LocalDate data);
}
