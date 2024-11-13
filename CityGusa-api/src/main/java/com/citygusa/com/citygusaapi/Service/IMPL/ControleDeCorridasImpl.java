package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Controller.ControleDeCorridasController;
import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridasEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoCorridasFoundException;
import com.citygusa.com.citygusaapi.Mapper.ControleCorridasMapper;
import com.citygusa.com.citygusaapi.Repository.ControleDeCorridasRepository;
import com.citygusa.com.citygusaapi.Service.ControleDeCorridasService;
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

    private final ControleDeCorridasRepository controleDeCorridasRepository;
    private ControleCorridasMapper controleCorridasMapper;

    @Autowired
    public ControleDeCorridasImpl(ControleDeCorridasRepository controleDeCorridasRepository, ControleCorridasMapper controleCorridasMapper) {
        this.controleDeCorridasRepository = controleDeCorridasRepository;
        this.controleCorridasMapper = controleCorridasMapper;
    }

    @Override
    @Transactional
    public Optional<ControleDeCorridasDto> saveCorridas(ControleCorridasEntity controleCorridasEntity) {
        logger.info("Tentando salvar correida : {}", controleCorridasEntity);
        ControleCorridasEntity corridasSaved = controleDeCorridasRepository.save(controleCorridasEntity);

        //calcular ritmo
        Integer minutosAcumulados = getMinutosAcumuladosDoDia(controleCorridasEntity.getCreatedAt());
        BigDecimal realTnAcumulado = getRealTnAcumulado(controleCorridasEntity.getCreatedAt());
        Integer minutos = 1440;

        BigDecimal minutosAcumuladosBigDecimal = new BigDecimal(minutosAcumulados);
        BigDecimal minutosConvertidos = new BigDecimal(minutos);
        BigDecimal ritmo = realTnAcumulado.divide(minutosAcumuladosBigDecimal, MathContext.DECIMAL128);
        BigDecimal resultadoRitmo=  ritmo.multiply(minutosConvertidos);
        Double resultadoDouble = resultadoRitmo.doubleValue();

        // Atualiza o campo ritmo na entidade ControleCorridasEntity
        corridasSaved.setRitmo(resultadoDouble);
        controleDeCorridasRepository.save(corridasSaved);

        ControleDeCorridasDto corridasDto = controleCorridasMapper.toDto(corridasSaved);
        logger.info("Corrida salva com sucesso: {}", corridasDto);
        return Optional.of(corridasDto);
    }

    @Override
    @Transactional
    public Integer getMinutosAcumuladosDoDia(LocalDate createdAt) {
        return controleDeCorridasRepository.findMinutosAcumuladosPorData(createdAt);
    }

    @Override
    public Double getMediaFosforo(LocalDate createdAt) {
        return controleDeCorridasRepository.findMediaFosforo(createdAt);
    }

    @Override
    public Double getMediaSilica(LocalDate createdAt) {
        return controleDeCorridasRepository.findMediaSilica(createdAt);
    }

    @Override
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
        List<ControleCorridasEntity> corridas = controleDeCorridasRepository.findAllByCreatedAt(createdAt);

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