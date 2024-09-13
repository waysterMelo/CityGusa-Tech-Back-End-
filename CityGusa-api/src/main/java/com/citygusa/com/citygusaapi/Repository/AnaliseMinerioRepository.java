package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import com.citygusa.com.citygusaapi.Entity.AnaliseQuimicaMineriosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnaliseMinerioRepository extends JpaRepository<AnaliseQuimicaMineriosEntity, Integer> {

    List<AnaliseQuimicaMineriosEntity> findAllByCreatedAt(LocalDate createdAt);
}
