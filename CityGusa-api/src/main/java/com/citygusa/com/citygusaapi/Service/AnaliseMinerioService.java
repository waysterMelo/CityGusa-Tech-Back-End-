package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.AnaliseMinerioDto;
import com.citygusa.com.citygusaapi.Dto.CadastrarMineriosDTO;
import com.citygusa.com.citygusaapi.Entity.CadastrarAnaliseMineriosEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnaliseMinerioService {

    Optional<AnaliseMinerioDto> save(CadastrarAnaliseMineriosEntity entity);

    List<AnaliseMinerioDto> getAllAnalisesMinerios(LocalDate createdAt) throws NoAnalisesFoundException;

    Double getSilica(LocalDate createdAt);

    Double getAluminio(LocalDate createdAt);

    Double getPpc(LocalDate createdAt);

    Double getFosforo(LocalDate createdAt);

    Double getManganes(LocalDate createdAt);

    Double getFerro(LocalDate createdAt);

    List<CadastrarMineriosDTO> returnPesquisaLote(String lote);
}
