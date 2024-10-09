package com.citygusa.com.citygusaapi.Service;


import com.citygusa.com.citygusaapi.Dto.AnaliseMinerioDto;
import com.citygusa.com.citygusaapi.Dto.CadastrarMineriosDTO;
import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CadastrarMInerioService {

    Optional<CadastrarMineriosDTO> save(CadastrarMineriosEntity cadastrarMineriosEntity);
    List<CadastrarMineriosDTO> returnAllCadastrarMinerios(LocalDate data);
    List<CadastrarMineriosDTO> getLotePesquisado(String lote);
    List<CadastrarMineriosDTO> getMinerios();
}
