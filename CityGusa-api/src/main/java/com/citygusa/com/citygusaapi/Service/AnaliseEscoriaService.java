package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.AnaliseEscoriaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseEscoriaEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AnaliseEscoriaService {
    ResponseEntity<AnaliseEscoriaDto> save(AnaliseEscoriaEntity entity);
    List<AnaliseEscoriaDto> getAllAnalisesEscoria(LocalDate createdAt) throws NoAnalisesFoundException;

    Double getCalcio(LocalDate createdAt) throws NoAnalisesFoundException;
    Double getSilicio(LocalDate createdAt) throws NoAnalisesFoundException;
    Double getAluminio(LocalDate createdAt) throws NoAnalisesFoundException;
    Double getManganes(LocalDate createdAt) throws NoAnalisesFoundException;
    Double getMagnesio(LocalDate createdAt) throws NoAnalisesFoundException;
    Double getFerro(LocalDate createdAt) throws NoAnalisesFoundException;

}