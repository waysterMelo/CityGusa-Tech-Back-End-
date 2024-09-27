package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Dto.AnaliseEscoriaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseEscoriaEntity;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusaEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnaliseEscoriaRepository extends JpaRepository<AnaliseEscoriaEntity, Integer> {
        List<AnaliseEscoriaEntity> findAllByCreatedAt(LocalDate createdAt);
}