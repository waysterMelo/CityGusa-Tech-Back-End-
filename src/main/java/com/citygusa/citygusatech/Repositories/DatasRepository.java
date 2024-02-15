package com.citygusa.citygusatech.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citygusa.citygusatech.Api.Entity.DatasEntity;

import java.time.LocalDate;

public interface DatasRepository extends JpaRepository<DatasEntity, Long> {

    boolean existsByData(LocalDate data);
}
