package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Dto.IdAndMinerioDto;
import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CadastrarMineriosRepository extends JpaRepository<CadastrarMineriosEntity, Integer> {

    List<CadastrarMineriosEntity> findCadastrarMineriosByCreatedAt(LocalDate data);
    List<CadastrarMineriosEntity> findByLote(String lote);

    @Query("select m.id, m.minerio from CadastrarMineriosEntity m")
    List<CadastrarMineriosEntity> findAllByMinerio();
}
