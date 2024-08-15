package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Controller.ControleDeCorridasController;
import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import com.citygusa.com.citygusaapi.Exceptions.NoCorridasFoundException;
import com.citygusa.com.citygusaapi.Repository.ControleDeCorridasRepository;
import com.citygusa.com.citygusaapi.Service.ControleDeCorridasService;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
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
        return dto;
    }

    @Override
    public Optional<ControleDeCorridasDto> saveCorridas(ControleCorridas controleCorridas) {
        logger.info("Tentando salvar correida : {}", controleCorridas);
        ControleCorridas corridasSaved = controleDeCorridasRepository.save(controleCorridas);

        //calcaular ritmo
        Integer minutosAcumulados = getMinutosAcumuladosDoDia(controleCorridas.getCreatedAt());
        BigDecimal realTnAcumulado = getRealTnAcumulado(controleCorridas.getCreatedAt());
        Integer minutos = 1440;

        BigDecimal minutosAcumuladosBigDecimal = new BigDecimal(minutosAcumulados);
        BigDecimal minutosConvertidos = new BigDecimal(minutos);
        BigDecimal ritmo = realTnAcumulado.divide(minutosAcumuladosBigDecimal, MathContext.DECIMAL128);
        BigDecimal resultadoRitmo=  ritmo.multiply(minutosConvertidos);
        Double resultadoDouble = resultadoRitmo.doubleValue();

        // Atualiza o campo ritmo na entidade ControleCorridas
        corridasSaved.setRitmo(resultadoDouble);
        controleDeCorridasRepository.save(corridasSaved);

        ControleDeCorridasDto corridasDto = convertToDto(corridasSaved);
        logger.info("Corrida salva com sucesso: {}", corridasDto);
        return Optional.of(corridasDto);
    }

    @Override
    @Transactional
    public Integer getMinutosAcumuladosDoDia(LocalDate createdAt) {
        return controleDeCorridasRepository.findMinutosAcumuladosPorData(createdAt);
    }

    @Override
    @Transactional
    public Double getMediaFosforo(LocalDate createdAt) {
        return controleDeCorridasRepository.findMediaFosforo(createdAt);
    }

    @Override
    @Transactional
    public Double getMediaSilica(LocalDate createdAt) {
        return controleDeCorridasRepository.findMediaSilica(createdAt);
    }

    @Override
    @Transactional
    public Double getMediaManganes(LocalDate createdAt) {
        return controleDeCorridasRepository.findMediaManganes(createdAt);
    }

    @Override
    public BigDecimal getRealTnAcumulado(LocalDate createdAt) {
        return controleDeCorridasRepository.findRealTnAcumulado(createdAt);
    }


    @Override
    @Transactional
    public List<ControleDeCorridasDto> getAllCorridasToday(LocalDate createdAt) {
        List<ControleCorridas> corridas = controleDeCorridasRepository.findAllByCreatedAt(createdAt);

        if (corridas.isEmpty()) {
            throw new NoCorridasFoundException("Não há corridas para retornar na data informada: " + createdAt);
        }

        Integer minutos = getMinutosAcumuladosDoDia(createdAt);
        Double mediaFosforo = getMediaFosforo(createdAt);
        Double mediaSilica = getMediaSilica(createdAt);
        Double mediaManganes = getMediaManganes(createdAt);
        BigDecimal realAcumulado = getRealTnAcumulado(createdAt);

        return corridas.stream()
                .map(corrida -> new ControleDeCorridasDto(corrida, minutos,
                        mediaFosforo, mediaSilica, mediaManganes, realAcumulado))
                .collect(Collectors.toList());
    }






}