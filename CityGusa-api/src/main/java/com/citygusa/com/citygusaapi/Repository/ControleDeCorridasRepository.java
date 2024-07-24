package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleDeCorridasRepository extends JpaRepository<ControleCorridas, Long> {
        
}
