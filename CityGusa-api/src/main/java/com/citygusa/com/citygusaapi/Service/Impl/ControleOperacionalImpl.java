package com.citygusa.com.citygusaapi.Service.Impl;

import com.citygusa.com.citygusaapi.Dto.ControleOperacionalDto;
import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoOperacionalFound;
import com.citygusa.com.citygusaapi.Mapper.ControleOperacionalMapper;
import com.citygusa.com.citygusaapi.Repository.ControleOperacionalRepository;
import com.citygusa.com.citygusaapi.Service.ControleOperacionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ControleOperacionalImpl implements ControleOperacionalService {

    private static final Logger logger = LoggerFactory.getLogger(ControleOperacionalImpl.class);

    private final ControleOperacionalRepository controleOperacionalRepository;
    private final ControleOperacionalMapper controleOperacionalMapper;

    @Autowired
    public ControleOperacionalImpl(ControleOperacionalRepository controleOperacionalRepository, ControleOperacionalMapper controleOperacionalMapper) {
        this.controleOperacionalRepository = controleOperacionalRepository;
        this.controleOperacionalMapper = controleOperacionalMapper;
    }


    @Override
    public Integer getCargaHora(LocalDate createdAt) {
        return controleOperacionalRepository.findCargaHoraByCreatedAt(createdAt);
    }

    @Override
    public Integer getGusaKg(LocalDate createdAt) {
        return controleOperacionalRepository.findGusaKgByCreatedAt(createdAt);
    }

    @Override
    public Integer getCargaAcumulada(LocalDate createdAt) {
        return controleOperacionalRepository.findCargaAcumulado(createdAt);
    }

    @Override
    public Integer getCargaAcumuladaSeca(LocalDate createdAt) {
        return controleOperacionalRepository.findCargaAcumuladoSeca(createdAt);
    }

    @Override
    public BigDecimal getUmidadeMedia(LocalDate createdAt) {
        return controleOperacionalRepository.findMediaUmidade(createdAt);
    }


    @Override
    public Double getDensidadeKgMedia(LocalDate createdAt) {
        return controleOperacionalRepository.findMediaDensidade(createdAt);
    }

    @Override
    public BigDecimal getMediaHora(LocalDate data) {
        return controleOperacionalRepository.findMediaHora(data);
    }

    @Override
    public Integer getUltimaDensidade(LocalDate data) {
        return controleOperacionalRepository.findUltimaDensidade(data);
    }

    @Override
    public BigInteger getMediaCarvaoEnfornado(LocalDate data) {
        return controleOperacionalRepository.findMediaPesoCarvao(data);
    }

    @Override
    public Optional<ControleOperacionalDto> save(ControleOperacionalEntity entity) {
        // Salvar entidade inicialmente para garantir que o primeiro registro exista
        ControleOperacionalEntity rs = controleOperacionalRepository.save(entity);

        // Salvar carga acumulada
        rs.setAcumuladoCarga(getCargaAcumulada(entity.getCreatedAt()));
        logger.info("Valor de carga acumulada: {}", rs.getAcumuladoCarga());

        // Salvar carga seca acumulada
        rs.setAcumuladoCargaSeca(getCargaAcumuladaSeca(entity.getCreatedAt()));
        logger.info("Valor de carga seca acumulada: {}", rs.getAcumuladoCargaSeca());

        // Calcular MEDIA/HORA
        rs.setMediaHoraCarga(getMediaHora(entity.getCreatedAt()));
        logger.info("Valor de média hora: {}", rs.getMediaHoraCarga());

        // Calcular RT
        BigDecimal horasDoDia = BigDecimal.valueOf(24);
        BigDecimal gusaConvertido = BigDecimal.valueOf(entity.getGusaKg());
        BigDecimal rt = rs.getMediaHoraCarga().multiply(gusaConvertido).multiply(horasDoDia)
                .setScale(2, RoundingMode.HALF_UP);
        rs.setRt(rt);
        logger.info("Valor de RT: {}", rt);

        // Calcular e salvar umidade média
        rs.setUmidadeMedia(getUmidadeMedia(entity.getCreatedAt()));
        logger.info("Valor de Média da umidade: {}", rs.getUmidadeMedia());

        // Calcular e salvar densidade média
        rs.setDensidadeMedia(getDensidadeKgMedia(entity.getCreatedAt()));
        logger.info("Valor de Média da densidade: {}", rs.getDensidadeMedia());

        // Calcular carvão peso CALC
        BigDecimal fatorDecimal = new BigDecimal(entity.getFatorBaseDensidadeSeca());
        BigDecimal umidade = BigDecimal.valueOf(entity.getUmidade());
        BigDecimal diferencaUmidadePercentual = umidade.compareTo(BigDecimal.valueOf(7)) > 0
                ? umidade.subtract(BigDecimal.valueOf(7)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        rs.setPesoCarvaoCalc(fatorDecimal.add(fatorDecimal.multiply(diferencaUmidadePercentual)));
        logger.info("Valor CALC: {}", rs.getPesoCarvaoCalc());

        // Calcular carvão enfornado
        BigDecimal carvaoAcumulado = BigDecimal.valueOf(entity.getAcumuladoKilos());
        BigDecimal cargaHora = BigDecimal.valueOf(entity.getCargaHora());
        rs.setCarvaoEnfornado(carvaoAcumulado.divide(cargaHora, 2, RoundingMode.HALF_UP));
        logger.info("Valor de carvão enfornado: {}", rs.getCarvaoEnfornado());

        // Salvar novamente para persistir os valores atualizados antes de calcular a média
        rs = controleOperacionalRepository.save(rs);

        // Calcular média do carvão enfornado e evitar null usando Optional
        BigInteger mediaCarvaoEnfornado = Optional.ofNullable(getMediaCarvaoEnfornado(entity.getCreatedAt()))
                .orElse(BigInteger.ZERO);
        rs.setCarvaoEnfornadoMedia(mediaCarvaoEnfornado);
        logger.info("Valor da média de carvão enfornado: {}", mediaCarvaoEnfornado);

        //calcular consumo em kilos por tonelada
        Integer carga = entity.getCargaHora();
        Integer gusa = entity.getGusaKg();
        Double acumuloKg = entity.getAcumuladoKilos();

        // Evitar divisão por zero
        if (gusa == 0 || carga == 0) {
            rs.setConsumoKg(BigDecimal.ZERO);
            logger.warn("Gusa ou carga é zero, evitando divisão por zero. Definindo consumo kg/t como 0.");
        } else {
            BigDecimal kilosTonelada = BigDecimal.valueOf(acumuloKg / (gusa * carga) * 1000)
                    .setScale(0, RoundingMode.HALF_UP); // Arredondar para inteiro
            rs.setConsumoKg(kilosTonelada);
            logger.info("Valor de consumo kg/t: {}", kilosTonelada);
        }


        //calculos de M3/T
        //ainda nao foi feito , entao utilizar null
        rs.setConsumoMetros(null);

        //calculo de +/-
        BigDecimal enfornadoCarvao = entity.getCarvaoEnfornado();
        BigDecimal calculadoCarvao = entity.getPesoCarvaoCalc();
        BigDecimal diferencaPositoOuNegativo = enfornadoCarvao.subtract(calculadoCarvao);
        rs.setPositivoNegativo(diferencaPositoOuNegativo);
        logger.info("Valor de enf {}", enfornadoCarvao);
        logger.info("Valor de calc {}", calculadoCarvao);
        logger.info("Valor de +/- {}", diferencaPositoOuNegativo);


        // Salvar apenas uma última vez com a média calculada
        controleOperacionalRepository.save(rs);

        return Optional.of(controleOperacionalMapper.toDto(rs));
    }

    @Override
    public List<ControleOperacionalDto> getAllDataByDate(LocalDate data) {
        List<ControleOperacionalEntity> rs = controleOperacionalRepository.findAllByCreatedAt(data);
        if (rs.isEmpty()){
            throw new NoOperacionalFound("Não há informações a serem retornadas");
        }
        return rs.stream().map(ControleOperacionalDto::new).toList();
    }

}
