package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ControleOperacionalRepository extends JpaRepository<ControleOperacionalEntity, Integer> {

    @Query("select o.cargaHora from ControleOperacionalEntity o where o.createdAt = :createdAt order by o.id desc limit 1")
    Integer findCargaHoraByCreatedAt(LocalDate createdAt);

    @Query("select o.gusaKg from ControleOperacionalEntity o where o.createdAt = :createdAt order by o.id desc limit 1")
    Integer findGusaKgByCreatedAt(LocalDate createdAt);

}