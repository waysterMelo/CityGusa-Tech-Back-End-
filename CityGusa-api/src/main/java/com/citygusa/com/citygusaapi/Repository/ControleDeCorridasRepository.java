package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ControleDeCorridasRepository extends JpaRepository<ControleCorridas, Long> {

    List<ControleCorridas> findAllByCreatedAt(LocalDate createdAt);

}
