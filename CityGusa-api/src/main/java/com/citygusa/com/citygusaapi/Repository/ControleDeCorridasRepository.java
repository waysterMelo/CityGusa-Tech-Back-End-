package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ControleDeCorridasRepository extends JpaRepository<ControleCorridas, Long> {

    @Query("SELECT COALESCE(SUM(c.minutos), 0) FROM ControleCorridas c WHERE c.createdAt = :data")
    Integer findMinutosAcumuladosPorData(@Param("data") LocalDate data);

    List<ControleCorridas> findAllByCreatedAt(LocalDate createdAt);

}
