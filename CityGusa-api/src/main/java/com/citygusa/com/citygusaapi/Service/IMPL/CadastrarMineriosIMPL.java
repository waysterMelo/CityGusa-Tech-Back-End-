package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.CadastrarMineriosDTO;
import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;
import com.citygusa.com.citygusaapi.Repository.CadastrarMineriosRepository;
import com.citygusa.com.citygusaapi.Service.CadastrarMInerioService;
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

    private final CadastrarMineriosRepository cadastrarMineriosRepo;
    private static final Logger log = LoggerFactory.getLogger(CadastrarMineriosIMPL.class);
    private final CadastrarMineriosRepository cadastrarMineriosRepository;


    @Autowired
    public CadastrarMineriosIMPL(CadastrarMineriosRepository cadastrarMineriosRepo, CadastrarMineriosRepository cadastrarMineriosRepository) {
        this.cadastrarMineriosRepo = cadastrarMineriosRepo;
        this.cadastrarMineriosRepository = cadastrarMineriosRepository;
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
    public List<CadastrarMineriosDTO> getLotePesquisado(String lote) {
       List<CadastrarMineriosEntity> lista =  cadastrarMineriosRepo.findByLote(lote);
       if (lista.isEmpty()) {
           return Collections.emptyList();
       }
       return lista.stream().map(CadastrarMineriosDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<CadastrarMineriosDTO> getMinerios() {
        List<CadastrarMineriosEntity> lista =  cadastrarMineriosRepository.findAllByMinerio();
        if (lista.isEmpty()) {
            return Collections.emptyList();
        }
        return lista.stream().map(CadastrarMineriosDTO::new).collect(Collectors.toList());
    }
}
