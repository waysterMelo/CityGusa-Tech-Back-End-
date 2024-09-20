package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Dto.CadastrarMineriosDTO;
import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;
import com.citygusa.com.citygusaapi.Repository.CadastrarMineriosRepo;
import com.citygusa.com.citygusaapi.Service.CadastrarMInerioService;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastrarMineriosIMPL implements CadastrarMInerioService {

    private final CadastrarMineriosRepo cadastrarMineriosRepo;

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
}
