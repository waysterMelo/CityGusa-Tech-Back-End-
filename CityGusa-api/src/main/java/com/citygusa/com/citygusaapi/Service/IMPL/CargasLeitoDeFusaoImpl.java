package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Controller.CargasLeitoDeFusaoController;
import com.citygusa.com.citygusaapi.Dto.CargasLeitoDeFusaoDto;
import com.citygusa.com.citygusaapi.Entity.CargasLeitoDeFusao;
import com.citygusa.com.citygusaapi.Repository.CadastrarMineriosRepository;
import com.citygusa.com.citygusaapi.Repository.CargasLeitoDeFusaoRepository;
import com.citygusa.com.citygusaapi.Service.CargasLeitoDeFusaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CargasLeitoDeFusaoImpl implements CargasLeitoDeFusaoService {

    private static final Logger logger = LoggerFactory.getLogger(CargasLeitoDeFusaoController.class);

    private final CargasLeitoDeFusaoRepository leitoDeFusaoRepository;
    private final CadastrarMineriosRepository cadastrarMineriosRepository;

    @Autowired
    public CargasLeitoDeFusaoImpl(CargasLeitoDeFusaoRepository leitoDeFusaoRepository, CadastrarMineriosRepository cadastrarMineriosRepository) {
        this.leitoDeFusaoRepository = leitoDeFusaoRepository;
        this.cadastrarMineriosRepository = cadastrarMineriosRepository;
    }

    public CargasLeitoDeFusaoDto convertToDto(CargasLeitoDeFusao cargasLeitoDeFusao) {
        CargasLeitoDeFusaoDto dto = new CargasLeitoDeFusaoDto();
        dto.setId(cargasLeitoDeFusao.getId());
        dto.setCreatedAt(cargasLeitoDeFusao.getCreatedAt());
        dto.setHoras(cargasLeitoDeFusao.getHoras());
        dto.setNumeroDaCarga(cargasLeitoDeFusao.getNumeroDaCarga());
        dto.setTipoMinerio(cargasLeitoDeFusao.getTipoMinerio());
        dto.setQuantidade(cargasLeitoDeFusao.getQuantidade());
        dto.setCalcareo(cargasLeitoDeFusao.getCalcareo());
        dto.setBauxita(cargasLeitoDeFusao.getBauxita());
        dto.setCoque(cargasLeitoDeFusao.getCoque());
        dto.setSecas(cargasLeitoDeFusao.getSecas());
        dto.setTotalCargas(cargasLeitoDeFusao.getTotalCargas());
        dto.setSucataGusa(cargasLeitoDeFusao.getSucataGusa());
        dto.setSucataAco(cargasLeitoDeFusao.getSucataAco());
        return dto;
    }


    @Override
    public Optional<CargasLeitoDeFusaoDto> salvarLeito(CargasLeitoDeFusao cargasLeitoDeFusao) {
        logger.info("Tentativa de salvar cargas leito de fusao {}", cargasLeitoDeFusao);
        CargasLeitoDeFusao entity = leitoDeFusaoRepository.save(cargasLeitoDeFusao);

        if (cargasLeitoDeFusao.getId() != null) {
            CargasLeitoDeFusaoDto dto = convertToDto(entity);
            logger.info("Informaçoes salva com sucesso, {}", dto);
            return Optional.of(dto);
        } else {

        }
        logger.warn("Falha ao salvar cargas leito de fusão: {}", cargasLeitoDeFusao);
        return Optional.empty();

    }


}
