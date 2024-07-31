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
import java.time.LocalDateTime;
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
        dto.setHoraInicio(controleCorridas.getHoraInicio());
        dto.setHoraFim(controleCorridas.getHoraFim());
        dto.setMinutos(controleCorridas.getMinutos());
        dto.setConchas(controleCorridas.getConchas());
        dto.setSilicio_visual(controleCorridas.getSilicio_visual());
        dto.setSilicio_real(controleCorridas.getSilicio_real());
        dto.setFosforo(controleCorridas.getFosforo());
        dto.setManganes(controleCorridas.getManganes());
        dto.setSilica(controleCorridas.getSilica());
        dto.setEscoria_inicio(controleCorridas.getEscoria_inicio());
        dto.setEscoria_fim(controleCorridas.getEscoria_fim());
        dto.setTipo_escoria(controleCorridas.getTipo_escoria());
        dto.setCarga_fundida_de(controleCorridas.getCarga_fundida_de());
        dto.setCarga_fundida_ate(controleCorridas.getCarga_fundida_ate());
        dto.setQuantidade(controleCorridas.getQuantidade());
        dto.setFe_gusa_kg(controleCorridas.getFe_gusa_kg());
        dto.setFerro(controleCorridas.getFerro());
        dto.setRealTn(controleCorridas.getRealTn());
        dto.setTempo_corrida_minutos(controleCorridas.getTempo_corrida_minutos());
        dto.setGusa_minuto(controleCorridas.getGusa_minuto());
        dto.setCarvao_kg(controleCorridas.getCarvao_kg());
        dto.setCarvao_metros(controleCorridas.getCarvao_metros());
        dto.setSopradores_1(controleCorridas.getSopradores_1());
        dto.setSopradores_2(controleCorridas.getSopradores_2());
        dto.setSopradores_3(controleCorridas.getSopradores_3());
        dto.setSopradores_4(controleCorridas.getSopradores_4());
        dto.setSopradores_5(controleCorridas.getSopradores_5());
        dto.setCreatedAt(controleCorridas.getCreatedAt());
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
    public List<ControleCorridas> getAllCorridasToday(LocalDate data) {

        List<ControleCorridas> corridas  = controleDeCorridasRepository.findAllByCreatedAt(data);

        if (corridas.isEmpty()){
            throw new NoCorridasFoundException("Não há corridas para retornar na data informada! " + data);
        }

        return corridas;
    }
}