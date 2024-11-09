package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.ControleCorridasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ControleDeCorridasRepository extends JpaRepository<ControleCorridasEntity, Long> {

    @Query("SELECT COALESCE(AVG(c.manganes), 0) FROM ControleCorridasEntity c where c.createdAt = :data")
    Double findMediaManganes(@Param("data") LocalDate data);

    @Query("SELECT COALESCE(AVG(c.fosforo), 0) FROM ControleCorridasEntity c where c.createdAt = :data")
    Double findMediaFosforo(@Param("data") LocalDate data);

    @Query("SELECT COALESCE(AVG(c.silica), 0) FROM ControleCorridasEntity c where c.createdAt = :data")
    Double findMediaSilica(@Param("data") LocalDate data);

    @Query("SELECT COALESCE(SUM(c.minutos), 0) FROM ControleCorridasEntity c WHERE c.createdAt = :data")
    Integer findMinutosAcumuladosPorData(@Param("data") LocalDate data);

    @Query("SELECT coalesce(sum(c.realTn), 0) from ControleCorridasEntity c where c.createdAt = :data")
    BigDecimal findRealTnAcumulado(@Param("data") LocalDate data);

    List<ControleCorridasEntity> findAllByCreatedAt(LocalDate createdAt);


}
