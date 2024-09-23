package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.CadastrarMineriosDTO;
import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;
import com.citygusa.com.citygusaapi.Service.IMPL.CadastrarMineriosIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/minerios")
public class CadastrarMineriosController {

    private final CadastrarMineriosIMPL service;

    @Autowired
    public CadastrarMineriosController(CadastrarMineriosIMPL service) {
        this.service = service;
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<CadastrarMineriosDTO> cadastrarMinerios(@RequestBody CadastrarMineriosEntity entity) {
      return service.save(entity).map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Não foi possivel realizar o cadastro de minerios."));
    }

    @GetMapping
    public ResponseEntity<List<CadastrarMineriosDTO>> getMinerios() {
        List<CadastrarMineriosDTO> rs = service.returnAllCadastrarMinerios(LocalDate.now());
        return ResponseEntity.ok().body(rs);
    }

    @GetMapping("/por-data")
    public ResponseEntity<?> getMineriosPorData(@RequestParam("data") LocalDate date) {
        List<CadastrarMineriosDTO> lista = service.returnAllCadastrarMinerios(date);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum minério encontrado para a data fornecida.");
        }
        return ResponseEntity.ok().body(lista);
    }
}
