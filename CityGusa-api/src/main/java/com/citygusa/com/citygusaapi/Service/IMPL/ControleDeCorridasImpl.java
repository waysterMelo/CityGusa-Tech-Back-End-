package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Controller.ControleDeCorridasController;
import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import com.citygusa.com.citygusaapi.Exceptions.NoCorridasFoundException;
import com.citygusa.com.citygusaapi.Repository.ControleDeCorridasRepository;
import com.citygusa.com.citygusaapi.Service.ControleDeCorridasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ControleDeCorridasImpl implements ControleDeCorridasService {

    private static final Logger logger = LoggerFactory.getLogger(ControleDeCorridasController.class);
    private static final DateTimeFormatter FORMATTED_DATE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter FORMATTED_HOUR = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    private ControleDeCorridasRepository controleDeCorridasRepository;

    private ControleDeCorridasDto convertToDto(ControleCorridas controleCorridas) {
        ControleDeCorridasDto dto = new ControleDeCorridasDto();
        dto.setId(controleCorridas.getId());
        dto.setData(controleCorridas.getData().format(FORMATTED_DATE));
        dto.setCacambas(controleCorridas.getCacambas());
        dto.setHoraAbertura(controleCorridas.getHoraAbertura().format(FORMATTED_HOUR));
        dto.setHoraTampa(controleCorridas.getHoraTampa().format(FORMATTED_HOUR));
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

    @Override
    public List<ControleDeCorridasDto> getAllCorridasByDate(ControleCorridas controleCorridas, String data) {

        LocalDate dataCorridaFormatada = LocalDate.parse(data, FORMATTED_DATE);

        List<ControleCorridas> corridas  = controleDeCorridasRepository.findByData(dataCorridaFormatada);

        if (corridas.isEmpty()){
            throw new NoCorridasFoundException("Não há corridas para retornar na data informada! " + dataCorridaFormatada);
        }

       return corridas.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
