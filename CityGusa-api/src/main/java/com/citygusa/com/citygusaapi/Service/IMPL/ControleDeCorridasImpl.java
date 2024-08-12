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

    private ControleDeCorridasDto convertToDto(ControleCorridas controleCorridas, Integer minutosAcumulados) {
        ControleDeCorridasDto dto = new ControleDeCorridasDto();
        dto.setId(controleCorridas.getId());
        dto.setHoraInicio(controleCorridas.getHoraInicio());
        dto.setHoraFim(controleCorridas.getHoraFim());
        dto.setMinutos(controleCorridas.getMinutos());
        dto.setConchas(controleCorridas.getConchas());
        dto.setSilicioVisual(controleCorridas.getSilicioVisual());
        dto.setSilicioReal(controleCorridas.getSilicioReal());
        dto.setFosforo(controleCorridas.getFosforo());
        dto.setManganes(controleCorridas.getManganes());
        dto.setSilica(controleCorridas.getSilica());
        dto.setEscoriaInicio(controleCorridas.getEscoriaInicio());
        dto.setEscoriaFim(controleCorridas.getEscoriaFim());
        dto.setTipoEscoria(controleCorridas.getTipoEscoria());
        dto.setCargaFundidaDe(controleCorridas.getCargaFundidaDe());
        dto.setCargaFundidaAte(controleCorridas.getCargaFundidaAte());
        dto.setQuantidade(controleCorridas.getQuantidade());
        dto.setFeGusaKg(controleCorridas.getFeGusaKg());
        dto.setFerro(controleCorridas.getFerro());
        dto.setRealTn(controleCorridas.getRealTn());
        dto.setTempoCorridaMinutos(controleCorridas.getTempoCorridaMinutos());
        dto.setGusaMinuto(controleCorridas.getGusaMinuto());
        dto.setCarvaoKg(controleCorridas.getCarvaoKg());
        dto.setCarvaoMetros(controleCorridas.getCarvaoMetros());
        dto.setSopradores1(controleCorridas.getSopradores1());
        dto.setSopradores2(controleCorridas.getSopradores2());
        dto.setSopradores3(controleCorridas.getSopradores3());
        dto.setSopradores4(controleCorridas.getSopradores4());
        dto.setSopradores5(controleCorridas.getSopradores5());
        dto.setCreatedAt(controleCorridas.getCreatedAt());
        dto.setTemperatura(controleCorridas.getTemperatura());
        dto.setMinutosAcumulados(minutosAcumulados);
        return dto;
    }

    @Override
    public Optional<ControleDeCorridasDto> saveCorridas(ControleCorridas controleCorridas) {
        logger.info("Tentando salvar correida : {}", controleCorridas);
        ControleCorridas corridasSaved = controleDeCorridasRepository.save(controleCorridas);
        Integer minutosAcumulados = getMinutosAcumulados(corridasSaved.getCreatedAt());
        ControleDeCorridasDto corridasDto = convertToDto(corridasSaved, minutosAcumulados);
        logger.info("Corrida salva com sucesso: {}", corridasDto, minutosAcumulados);
        return Optional.of(corridasDto);
    }

    @Override
    public List<ControleDeCorridasDto> getAllCorridasToday(LocalDate createdAt) {
        List<ControleCorridas> corridas = controleDeCorridasRepository.findAllByCreatedAt(createdAt);
        if (corridas.isEmpty()) {
            throw new NoCorridasFoundException("Não há corridas para retornar na data informada: " + createdAt);
        }

        Integer minutosAcumulados = getMinutosAcumulados(createdAt);

        return corridas.stream()
                .map(corrida -> new ControleDeCorridasDto(corrida, minutosAcumulados))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getMinutosAcumulados(LocalDate createdAt) {
        return controleDeCorridasRepository.findMinutosAcumuladosPorData(createdAt);
    }

}