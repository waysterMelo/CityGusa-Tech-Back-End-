package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.ControleOperacionalDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridasEntity;
import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoOperacionalFound;
import com.citygusa.com.citygusaapi.Mapper.ControleOperacionalMapper;
import com.citygusa.com.citygusaapi.Repository.ControleOperacionalRepository;
import com.citygusa.com.citygusaapi.Service.ControleOperacionalService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public String getHoras(String horas) {
        return controleOperacionalRepository.findHoras();
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

    //pegar horas e somar mais 1
    public Integer getHorasPlus(ControleOperacionalEntity entity){
        String horas = getHoras(entity.getHoras());
        String horasFormatada = horas.substring(0,2);
        Integer horasInteiro = Integer.parseInt(horasFormatada) + 1;
        return horasInteiro;
    }


    @Override
    public Optional<ControleOperacionalDto> save(ControleOperacionalEntity entity) {
       ControleOperacionalEntity rs = controleOperacionalRepository.save(entity);

        Integer horasInteiro = getHorasPlus(entity);

        //salvar carga acumulada
        Integer cargaAcumulada = getCargaAcumulada(entity.getCreatedAt());
        rs.setAcumuladoCarga(cargaAcumulada);
        logger.info("Valor de carga acumulada: {}", cargaAcumulada);


        //salvar cargaseca acumulada
        Integer cargaSecaAcumulada = getCargaAcumuladaSeca(entity.getCreatedAt());
        rs.setAcumuladoCargaSeca(cargaSecaAcumulada);
        logger.info("Valor de carga seca acumulada é: {}", cargaSecaAcumulada);


        //calcular MEDIA/HORA
        Integer gusaKg = getGusaKg(entity.getCreatedAt());
        Double mediaHora = (double) cargaAcumulada / horasInteiro;
        BigDecimal mediaHoraArredondado = new BigDecimal(mediaHora).setScale(2, RoundingMode.HALF_UP);
        rs.setMediaHoraCarga(mediaHoraArredondado);
        logger.info("Valor de Media/Hora: {}", mediaHoraArredondado);


        //calcular rt
        BigDecimal gusaConvertido = new BigDecimal(gusaKg);
        BigDecimal rtCalculado = mediaHoraArredondado.multiply(gusaConvertido).multiply(new BigDecimal(24));
        Integer rtConvertido = rtCalculado.divide(new BigDecimal(1000), 0, RoundingMode.DOWN).intValue();
        rs.setRt(rtConvertido);
        logger.info("Valor de Rt: {}", rtConvertido);

        //calcular e salvar umidade media
        BigDecimal umidadeMedia = getUmidadeMedia(entity.getCreatedAt());
        rs.setUmidadeMedia(umidadeMedia);
        logger.info("Valor de Media da umidade é: {}", umidadeMedia);

        //calcular e salvar densidade media
        Double densidadeKgMedia = getDensidadeKgMedia(entity.getCreatedAt());
        rs.setDensidadeMedia(densidadeKgMedia);
        logger.info("Valor de Media da densidade é: {}", densidadeKgMedia);


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
        return rs.stream().map(result -> new ControleOperacionalDto(result)).collect(Collectors.toList());
    }

}
