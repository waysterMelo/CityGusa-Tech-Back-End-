package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridas;

import java.util.List;
import java.util.Optional;

public interface ControleDeCorridasService {

    Optional<ControleDeCorridasDto> saveCorridas(ControleCorridas controleCorridas);
    List<ControleDeCorridasDto> getAllCorridas(ControleCorridas controleCorridas);

}
