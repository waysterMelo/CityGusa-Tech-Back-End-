package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Dto.CadastrarMineriosDTO;
import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CadastrarMineriosRepo extends JpaRepository<CadastrarMineriosEntity, Integer> {

    List<CadastrarMineriosEntity> findCadastrarMineriosByCreatedAt(LocalDate data);
}
