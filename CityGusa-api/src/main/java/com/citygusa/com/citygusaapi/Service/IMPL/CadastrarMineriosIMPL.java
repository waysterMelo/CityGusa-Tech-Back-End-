package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.CadastrarMineriosDTO;
import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;
import com.citygusa.com.citygusaapi.Repository.CadastrarMineriosRepo;
import com.citygusa.com.citygusaapi.Service.CadastrarMInerioService;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CadastrarMineriosIMPL implements CadastrarMInerioService {

    private final CadastrarMineriosRepo cadastrarMineriosRepo;
    private static final Logger log = LoggerFactory.getLogger(CadastrarMineriosIMPL.class);


    @Autowired
    public CadastrarMineriosIMPL(CadastrarMineriosRepo cadastrarMineriosRepo) {
        this.cadastrarMineriosRepo = cadastrarMineriosRepo;
    }

    @Override
    @Transactional
    public Optional<CadastrarMineriosDTO> save(CadastrarMineriosEntity cadastrarMineriosEntity) {
         CadastrarMineriosEntity saved = cadastrarMineriosRepo.save(cadastrarMineriosEntity);
         CadastrarMineriosDTO dto = new CadastrarMineriosDTO(saved);
         return Optional.of(dto);
    }

    @Override
    public List<CadastrarMineriosDTO> returnAllCadastrarMinerios(LocalDate data) {
        List<CadastrarMineriosEntity> lista = cadastrarMineriosRepo.findCadastrarMineriosByCreatedAt(data);
        if (lista.isEmpty()) {
            // Logando uma mensagem de que a lista está vazia
            log.info("Nenhum minério cadastrado para a data: " + data);
            return Collections.emptyList(); // Retorna uma lista vazia
        }
        return lista.stream().map(CadastrarMineriosDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<CadastrarMineriosDTO> returnPesquisaLote(String lote) {
        List<CadastrarMineriosEntity> lista = cadastrarMineriosRepo.findByLote(lote);
        return lista.stream().map(CadastrarMineriosDTO::new).collect(Collectors.toList());
    }
}
