package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Controller.ControleDeCorridasController;
import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import com.citygusa.com.citygusaapi.Repository.ControleDeCorridasRepository;
import com.citygusa.com.citygusaapi.Service.ControleDeCorridasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ControleDeCorridasImpl implements ControleDeCorridasService {

    private static final Logger logger = LoggerFactory.getLogger(ControleDeCorridasController.class);


    @Autowired
    private ControleDeCorridasRepository controleDeCorridasRepository;


    private ControleDeCorridasDto convertToDto(ControleCorridas controleCorridas) {
        ControleDeCorridasDto dto = new ControleDeCorridasDto();
        dto.setId(controleCorridas.getId());
        dto.setData(controleCorridas.getData());
        dto.setCacambas(controleCorridas.getCacambas());
        dto.setHoraAbertura(controleCorridas.getHoraAbertura());
        dto.setHoraTampa(controleCorridas.getHoraTampa());
        dto.setTemperatura(controleCorridas.getTemperatura());
        dto.setReducao(controleCorridas.getReducao());
        dto.setReservaFundida(controleCorridas.getReservaFundida());
        dto.setEscoriaVisual(controleCorridas.getEscoriaVisual());
        dto.setProducao(controleCorridas.getProducao());
        dto.setProducaoAcumulada(controleCorridas.getProducaoAcumulada());
        dto.setMedia(controleCorridas.getMedia());
        dto.setCecDiaM3(controleCorridas.getCecDiaM3());
        dto.setCecDiaKg(controleCorridas.getCecDiaKg());
        return dto;
    }

    @Override
    public Optional<ControleDeCorridasDto> saveCorridas(ControleCorridas controleCorridas) {
        logger.info("Tentando salvar correida : {}", controleCorridas);
        ControleCorridas corridasSaved = controleDeCorridasRepository.save(controleCorridas);
        ControleDeCorridasDto corridasDto = convertToDto(corridasSaved);
        logger.info("Corrida salva com sucesso: {}", corridasDto);
        return Optional.of(corridasDto);
    }
}
