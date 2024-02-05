package com.citygusa.citygusatech.Repositories;

import com.citygusa.citygusatech.Entity.HorasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface HorasRepository extends JpaRepository<HorasEntity, Long> {
}
