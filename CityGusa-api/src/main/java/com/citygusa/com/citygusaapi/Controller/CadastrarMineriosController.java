package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.CadastrarMineriosDTO;
import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;
import com.citygusa.com.citygusaapi.Service.IMPL.CadastrarMineriosIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/cadastrar-minerios")
public class CadastrarMineriosController {

    private final CadastrarMineriosIMPL service;

    @Autowired
    public CadastrarMineriosController(CadastrarMineriosIMPL service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CadastrarMineriosDTO> cadastrarMinerios(@RequestBody CadastrarMineriosEntity entity) {
      return service.save(entity).map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Não foi possivel realizar o cadastro de minerios."));
    }
}
