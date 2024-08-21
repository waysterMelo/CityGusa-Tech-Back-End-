package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.CargasLeitoDeFusao;
import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CargasLeitoDeFusaoRepository extends JpaRepository<CargasLeitoDeFusao, Integer> {



}
