package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ControleDeCorridasService {

    Optional<ControleDeCorridasDto> saveCorridas(ControleCorridas controleCorridas);

    List<ControleDeCorridasDto> getAllCorridasToday(LocalDate createdAt);

    Integer getMinutosAcumuladosDoDia(LocalDate createdAt);

    Double getMediaFosforo(LocalDate createdAt);

    Double getMediaSilica(LocalDate createdAt);

}
