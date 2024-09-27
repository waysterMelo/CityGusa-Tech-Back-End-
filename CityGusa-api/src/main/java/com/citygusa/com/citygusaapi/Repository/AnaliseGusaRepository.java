package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.AnaliseGusaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnaliseGusaRepository extends JpaRepository<AnaliseGusaEntity, Integer> {

    List<AnaliseGusaEntity> findAllByCreatedAt(LocalDate createdAt);
}
