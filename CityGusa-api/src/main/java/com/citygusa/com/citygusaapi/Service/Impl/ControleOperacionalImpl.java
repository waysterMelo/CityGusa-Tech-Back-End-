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
    public Optional<ControleOperacionalDto> save(ControleOperacionalEntity entity) {
       ControleOperacionalEntity rs = controleOperacionalRepository.save(entity);


        //salvar carga acumulada
        Integer cargaAcumulada = getCargaAcumulada(entity.getCreatedAt());
        rs.setAcumuladoCarga(cargaAcumulada);
        logger.info("Valor de carga acumulada: {}", cargaAcumulada);


        //salvar cargaseca acumulada
        Integer cargaSecaAcumulada = getCargaAcumuladaSeca(entity.getCreatedAt());
        rs.setAcumuladoCargaSeca(cargaSecaAcumulada);
        logger.info("Valor de carga seca acumulada é: {}", cargaSecaAcumulada);


        //calcular MEDIA/HORA
        BigDecimal mediaHora = getMediaHora(entity.getCreatedAt());
        rs.setMediaHoraCarga(mediaHora);
        logger.info("Valor de media hora : {}", mediaHora);


        //calcular rt
        BigDecimal horasDoDia = BigDecimal.valueOf(24);
        BigDecimal gusaConvertido = BigDecimal.valueOf(entity.getGusaKg());
        BigDecimal rt = mediaHora.multiply(gusaConvertido).multiply(horasDoDia);
        BigDecimal rtArredondado = rt.setScale(2, RoundingMode.HALF_UP);
        rs.setRt(rtArredondado);
        logger.info("Valor de RT : {}", rtArredondado);


        //calcular e salvar umidade media
        BigDecimal umidadeMedia = getUmidadeMedia(entity.getCreatedAt());
        rs.setUmidadeMedia(umidadeMedia);
        logger.info("Valor de Media da umidade é: {}", umidadeMedia);

        //calcular e salvar densidade media
        Double densidadeKgMedia = getDensidadeKgMedia(entity.getCreatedAt());
        rs.setDensidadeMedia(densidadeKgMedia);
        logger.info("Valor de Media da densidade é: {}", densidadeKgMedia);

        //calcular carvao peso CALC
        BigInteger fator =  entity.getFatorBaseDensidadeSeca();
        BigDecimal umidade = BigDecimal.valueOf(entity.getUmidade());
        BigDecimal diferencaUmidadePercentual = BigDecimal.ZERO;

        if (umidade.compareTo(BigDecimal.valueOf(7)) > 0){
            BigDecimal diferencaUmidade  = umidade.subtract(BigDecimal.valueOf(7));
            diferencaUmidadePercentual = diferencaUmidade.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        }
        BigDecimal fatorDecimal = new BigDecimal(fator);
        BigDecimal ajuste = fatorDecimal.multiply(diferencaUmidadePercentual);
        BigDecimal resultadoCalc = fatorDecimal.add(ajuste);
        rs.setPesoCarvaoCalc(resultadoCalc);
        logger.info("Valor CALC: {}", resultadoCalc);


        //calcular carao enfornado
        BigDecimal carvaoAcumulado = BigDecimal.valueOf(entity.getAcumuladoKilos());
        BigDecimal cargaHora = BigDecimal.valueOf(entity.getCargaHora());
        BigDecimal carvaoEnfornado = carvaoAcumulado.divide(cargaHora, 2, RoundingMode.HALF_UP);
        rs.setCarvaoEnfornado(carvaoEnfornado);
        logger.info("Valor de carvao enfornado é: {}", carvaoEnfornado);



        //salvar novamente
        controleOperacionalRepository.save(rs);

       ControleOperacionalDto dto = controleOperacionalMapper.toDto(rs);
        return Optional.of(dto);
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
