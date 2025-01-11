package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.ControleOperacionalDto;
import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ControleOperacionalService {

    Optional<ControleOperacionalDto> save(ControleOperacionalEntity entity);
    Integer getCargaHora(LocalDate createdAt);
    Integer getGusaKg(LocalDate createdAt);
    Integer getCargaAcumulada(LocalDate createdAt);
    Integer getCargaAcumuladaSeca(LocalDate createdAt);
    BigDecimal getUmidadeMedia(LocalDate createdAt);
    Double getDensidadeKgMedia(LocalDate createdAt);
    List<ControleOperacionalDto> getAllDataByDate(LocalDate data);
    BigDecimal getMediaHora(LocalDate data);
    Integer getUltimaDensidade(LocalDate data);
}
