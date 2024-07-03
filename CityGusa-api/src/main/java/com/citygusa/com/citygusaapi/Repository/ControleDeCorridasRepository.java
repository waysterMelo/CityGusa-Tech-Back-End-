package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ControleDeCorridasRepository extends JpaRepository<ControleCorridas, Long> {
        List<ControleCorridas> findByData(LocalDate data);
}
