package com.citygusa.com.citygusaapi.Repository;

import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseGusaRepository extends JpaRepository<AnaliseGusa, Integer> {

}
