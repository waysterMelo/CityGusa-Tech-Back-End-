package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.CargasLeitoDeFusao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargasLeitoDeFusaoRepository extends JpaRepository<CargasLeitoDeFusao, Integer> {


}
