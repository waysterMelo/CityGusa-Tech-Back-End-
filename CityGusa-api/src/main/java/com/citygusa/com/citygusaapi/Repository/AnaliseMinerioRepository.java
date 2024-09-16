package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.AnaliseMineriosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnaliseMinerioRepository extends JpaRepository<AnaliseMineriosEntity, Integer> {

    List<AnaliseMineriosEntity> findAllByCreatedAt(LocalDate createdAt);
}
