package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.AnaliseEscoriaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseEscoriaEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Repository.AnaliseEscoriaRepository;
import com.citygusa.com.citygusaapi.Service.AnaliseEscoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnaliseEscoriaImpl implements AnaliseEscoriaService {


    private final AnaliseEscoriaRepository analiseEscoriaRepository;

    @Override
    public Double getCalcio(LocalDate createdAt){
        return analiseEscoriaRepository.findCalcioByCreatedAt(createdAt);
    }

    @Override
    public Double getSilicio(LocalDate createdAt){
        return analiseEscoriaRepository.findSilicioByCreatedAt(createdAt);
    }

    @Override
    public Double getAluminio(LocalDate createdAt) {
        return analiseEscoriaRepository.findAluminioByCreatedAt(createdAt);
    }

    @Override
    public Double getManganes(LocalDate createdAt) {
        return analiseEscoriaRepository.findManganesByCreatedAt(createdAt);
    }

    @Override
    public Double getMagnesio(LocalDate createdAt) {
        return analiseEscoriaRepository.findMagnesioByCreatedAt(createdAt);
    }

    @Override
    public Double getFerro(LocalDate createdAt) {
        return analiseEscoriaRepository.findFerroByCreatedAt(createdAt);
    }

    @Autowired
    public AnaliseEscoriaImpl(AnaliseEscoriaRepository analiseEscoriaRepository) {
        this.analiseEscoriaRepository = analiseEscoriaRepository;
    }

    @Override
    public ResponseEntity<AnaliseEscoriaDto> save(AnaliseEscoriaEntity entity) {
        AnaliseEscoriaEntity analiseEscoriaEntity =  analiseEscoriaRepository.save(entity);
        Double silicio  = getSilicio(analiseEscoriaEntity.getCreatedAt());
        Double aluminio = getAluminio(analiseEscoriaEntity.getCreatedAt());
        Double manganes = getManganes(analiseEscoriaEntity.getCreatedAt());
        Double ferro = getFerro(analiseEscoriaEntity.getCreatedAt());
        Double calcio = getCalcio(analiseEscoriaEntity.getCreatedAt());
        Double magnesio = getMagnesio(analiseEscoriaEntity.getCreatedAt());

        Double indiceB = calcio / silicio;

        Double indiceF = (silicio + aluminio) / calcio;

        indiceB = new BigDecimal(indiceB).setScale(2, RoundingMode.HALF_UP).doubleValue();
        indiceF = new BigDecimal(indiceF).setScale(2, RoundingMode.HALF_UP).doubleValue();

        analiseEscoriaEntity.setIndexB(indiceB);
        analiseEscoriaEntity.setIndexF(indiceF);

        // Salva novamente a entidade com os índices atualizados
        analiseEscoriaEntity = analiseEscoriaRepository.save(analiseEscoriaEntity);

        // Converte para DTO para retorno
        AnaliseEscoriaDto analiseEscoriaDto = new AnaliseEscoriaDto(analiseEscoriaEntity);

        return new ResponseEntity<>(analiseEscoriaDto, HttpStatus.CREATED);
    }

    @Override
    public List<AnaliseEscoriaDto> getAllAnalisesEscoria(LocalDate createdAt) throws NoAnalisesFoundException {
        List<AnaliseEscoriaEntity> lista_analises = analiseEscoriaRepository.findAllByCreatedAt(createdAt);
        if (lista_analises.isEmpty()) {
            throw new NoAnalisesFoundException("Não há análises para retornar na data informada: " + createdAt);
        }
        return lista_analises.stream().map(AnaliseEscoriaDto::new).collect(Collectors.toList());
    }



}
