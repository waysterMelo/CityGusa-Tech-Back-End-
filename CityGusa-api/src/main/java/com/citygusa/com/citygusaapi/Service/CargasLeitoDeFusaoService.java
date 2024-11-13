package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.CargasLeitoDeFusaoDto;
import com.citygusa.com.citygusaapi.Entity.CargasLeitoDeFusao;

import java.util.Optional;

public interface CargasLeitoDeFusaoService {

    Optional<CargasLeitoDeFusaoDto> salvarLeito(CargasLeitoDeFusao cargasLeitoDeFusao);

}
