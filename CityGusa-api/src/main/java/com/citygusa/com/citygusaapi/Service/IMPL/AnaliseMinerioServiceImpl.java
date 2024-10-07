package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.AnaliseMinerioDto;
import com.citygusa.com.citygusaapi.Entity.CadastrarAnaliseMineriosEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Repository.AnaliseMinerioRepository;
import com.citygusa.com.citygusaapi.Repository.CadastrarMineriosRepository;
import com.citygusa.com.citygusaapi.Service.AnaliseMinerioService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnaliseMinerioServiceImpl implements AnaliseMinerioService {

    private static final Logger logger = LoggerFactory.getLogger(AnaliseMinerioServiceImpl.class);

    private final AnaliseMinerioRepository analiseMinerioRepository;
    private final CadastrarMineriosRepository cadastrarMineriosRepo;

    @Autowired
    public AnaliseMinerioServiceImpl(AnaliseMinerioRepository analiseMinerioRepository, CadastrarMineriosRepository cadastrarMineriosRepo) {
        this.analiseMinerioRepository = analiseMinerioRepository;
        this.cadastrarMineriosRepo = cadastrarMineriosRepo;
    }

    private AnaliseMinerioDto convertToDto(CadastrarAnaliseMineriosEntity entity){
        AnaliseMinerioDto dto = new AnaliseMinerioDto();
        dto.setId(entity.getId());
        dto.setMinerio(entity.getMinerio());
        dto.setLote(entity.getLote());
        dto.setPatio(entity.getPatio());
        dto.setTonelada(entity.getTonelada());
        dto.setFerro(entity.getFerro());
        dto.setSilica(entity.getSilica());
        dto.setAluminio(entity.getAluminio());
        dto.setFosforo(entity.getFosforo());
        dto.setManganes(entity.getManganes());
        dto.setPpc(entity.getPpc());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setFechamento(entity.getFechamento());
        return dto;
    }

    @Override
    public Double getSilica(LocalDate createdAt) {
        return analiseMinerioRepository.findSilicaByCreatedAt(createdAt);
    }

    @Override
    public Double getAluminio(LocalDate createdAt) {
        return analiseMinerioRepository.findAluminioByCreatedAt(createdAt);
    }

    @Override
    public Double getPpc(LocalDate createdAt) {
        return analiseMinerioRepository.findPpcByCreatedAt(createdAt);
    }

    @Override
    public Double getFosforo(LocalDate createdAt) {
        return analiseMinerioRepository.findFosforoByCreatedAt(createdAt);
    }

    @Override
    public Double getManganes(LocalDate createdAt) {
        return analiseMinerioRepository.findManganesByCreatedAt(createdAt);
    }

    @Override
    public Double getFerro(LocalDate createdAt) {
        return analiseMinerioRepository.findFerroByCreatedAt(createdAt);
    }

    @Override
    @Transactional
    public Optional<AnaliseMinerioDto> save(CadastrarAnaliseMineriosEntity entity) {

            logger.info("Tentando salvar informação : {}", entity);
            CadastrarAnaliseMineriosEntity analiseMinerios = analiseMinerioRepository.save(entity);

            //dados para fechamento
            Double silica = getSilica(analiseMinerios.getCreatedAt());
            Double fosforo = getFosforo(analiseMinerios.getCreatedAt());
            Double aluminio = getAluminio(analiseMinerios.getCreatedAt());
            Double ppc = getPpc(analiseMinerios.getCreatedAt());
            Double manganes = getManganes(analiseMinerios.getCreatedAt());
            Double ferro = getFerro(analiseMinerios.getCreatedAt());
            Double random = 1.43;

            //calcula o fechamento
            Double hematita = ferro * random;
            double fechamento = hematita + silica + fosforo + aluminio + ppc + manganes;

            BigDecimal fechamentoArredondado = new BigDecimal(fechamento).setScale(2, RoundingMode.HALF_UP);

            analiseMinerios.setFechamento(fechamentoArredondado.doubleValue());

            analiseMinerioRepository.save(analiseMinerios);

            AnaliseMinerioDto dto = convertToDto(analiseMinerios);

            logger.info("Analise salva com sucesso: {}", hematita);

            return Optional.of(dto);
    }

    @Override
    public List<AnaliseMinerioDto> getAllAnalisesMinerios(LocalDate createdAt) throws NoAnalisesFoundException {
        List<CadastrarAnaliseMineriosEntity> lista = analiseMinerioRepository.findAllByCreatedAt(createdAt);
        if (lista.isEmpty()) {
            throw new NoAnalisesFoundException("Não há análises para retornar na data informada: " + createdAt);
        }
        return lista.stream().map(AnaliseMinerioDto::new).collect(Collectors.toList());
    }

}
