package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Dto.AnaliseEscoriaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseEscoriaEntity;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusaEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnaliseEscoriaRepository extends JpaRepository<AnaliseEscoriaEntity, Integer> {
        List<AnaliseEscoriaEntity> findAllByCreatedAt(LocalDate createdAt);

        @Query("select a.calcio from AnaliseEscoriaEntity  a where a.createdAt = :createdAt order by a.id desc  limit 1")
        Double findCalcioByCreatedAt(LocalDate createdAt);

        @Query("select a.silicio from AnaliseEscoriaEntity  a where a.createdAt = :createdAt order by a.id desc  limit 1")
        Double findSilicioByCreatedAt(LocalDate createdAt);

        @Query("select a.aluminio from AnaliseEscoriaEntity  a where a.createdAt = :createdAt order by a.id desc  limit 1")
        Double findAluminioByCreatedAt(LocalDate createdAt);

        @Query("select a.magnesio from AnaliseEscoriaEntity  a where a.createdAt = :createdAt order by a.id desc  limit 1")
        Double findMagnesioByCreatedAt(LocalDate createdAt);

        @Query("select a.ferro from AnaliseEscoriaEntity  a where a.createdAt = :createdAt order by a.id desc  limit 1")
        Double findFerroByCreatedAt(LocalDate createdAt);

        @Query("select a.manganes from AnaliseEscoriaEntity  a where a.createdAt = :createdAt order by a.id desc  limit 1")
        Double findManganesByCreatedAt(LocalDate createdAt);

}