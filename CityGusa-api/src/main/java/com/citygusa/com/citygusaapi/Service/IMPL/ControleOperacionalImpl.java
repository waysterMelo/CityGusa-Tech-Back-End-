package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.ControleOperacionalDto;
import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import com.citygusa.com.citygusaapi.Mapper.ControleOperacionalMapper;
import com.citygusa.com.citygusaapi.Repository.ControleOperacionalRepository;
import com.citygusa.com.citygusaapi.Service.ControleOperacionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
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
    public String getHoras(String horas) {
        return controleOperacionalRepository.findHoras();
    }

    @Override
    public Integer getCargaAcumulada(LocalDate createdAt) {
        return controleOperacionalRepository.findCargaAcumulado(createdAt);
    }


    @Override
    public Optional<ControleOperacionalDto> save(ControleOperacionalEntity entity) {
       ControleOperacionalEntity rs = controleOperacionalRepository.save(entity);

        //pegar horas e somar mais 1
       String horas = getHoras(rs.getHoras());
       String horasFormatada = horas.substring(0,2);
       Integer horasInteiro = Integer.parseInt(horasFormatada) - 1;
        logger.info("Valor de horas: {}", horasInteiro);

        //retornar carga acumulada
        Integer cargaAcumulada = getCargaAcumulada(entity.getCreatedAt());
        rs.setAcumuladoCarga(cargaAcumulada);
        logger.info("Valor de carga acumulada: {}", cargaAcumulada);

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

        //salvar novamente
        controleOperacionalRepository.save(rs);

       ControleOperacionalDto dto = controleOperacionalMapper.toDto(rs);
        return Optional.of(dto);
    }



}
