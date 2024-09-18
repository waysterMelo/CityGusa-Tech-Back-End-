package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.AnaliseMineriosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnaliseMinerioRepository extends JpaRepository<AnaliseMineriosEntity, Integer> {

    List<AnaliseMineriosEntity> findAllByCreatedAt(LocalDate createdAt);

    @Query("SELECT c.silica FROM AnaliseMineriosEntity c WHERE c.createdAt = :data ORDER BY c.id DESC limit 1")
    Double findSilicaByCreatedAt(@Param("data") LocalDate data);

    @Query("SELECT c.aluminio FROM AnaliseMineriosEntity c where c.createdAt = :data ORDER BY c.id DESC limit 1")
    Double findAluminioByCreatedAt(@Param("data") LocalDate data);

    @Query("SELECT c.fosforo FROM AnaliseMineriosEntity c where c.createdAt = :data ORDER BY c.id DESC limit 1")
    Double findFosforoByCreatedAt(@Param("data") LocalDate data);

    @Query("SELECT c.manganes FROM AnaliseMineriosEntity c where c.createdAt = :data ORDER BY c.id DESC limit 1")
    Double findManganesByCreatedAt(@Param("data") LocalDate data);

    @Query("SELECT c.ppc FROM AnaliseMineriosEntity c where c.createdAt = :data ORDER BY c.id DESC limit 1")
    Double findPpcByCreatedAt(@Param("data") LocalDate data);

    @Query("SELECT c.ferro FROM AnaliseMineriosEntity c where c.createdAt = :data ORDER BY c.id DESC limit 1")
    Double findFerroByCreatedAt(@Param("data") LocalDate data);

}
