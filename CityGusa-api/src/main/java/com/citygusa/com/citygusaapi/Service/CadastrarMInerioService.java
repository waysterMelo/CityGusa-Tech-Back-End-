package com.citygusa.com.citygusaapi.Service;


import com.citygusa.com.citygusaapi.Dto.CadastrarMineriosDTO;
import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CadastrarMInerioService {

    Optional<CadastrarMineriosDTO> save(CadastrarMineriosEntity cadastrarMineriosEntity);
    List<CadastrarMineriosDTO> returnAllCadastrarMinerios(LocalDate data);
}
