package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.AnaliseMinerioDto;
import com.citygusa.com.citygusaapi.Dto.CadastrarMineriosDTO;
import com.citygusa.com.citygusaapi.Entity.CadastrarAnaliseMineriosEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Service.IMPL.AnaliseMinerioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value = "/analise-minerio")
public class AnaliseQuimicaMineriosController {

    private final AnaliseMinerioServiceImpl service;

    @Autowired
    public AnaliseQuimicaMineriosController(AnaliseMinerioServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Optional<AnaliseMinerioDto> salvarAnaliseMinerio(@RequestBody CadastrarAnaliseMineriosEntity analiseQuimicaMineriosEntity) {
        return service.save(analiseQuimicaMineriosEntity);
    }

    @GetMapping
    public ResponseEntity<List<AnaliseMinerioDto>> listasAnalisesMinerios() throws NoAnalisesFoundException {
        List<AnaliseMinerioDto> analises = service.getAllAnalisesMinerios(LocalDate.now());
        return ResponseEntity.ok(analises);
    }

    @GetMapping("/por-data")
    public ResponseEntity<List<AnaliseMinerioDto>> searchAnalisesByDate(@RequestParam("data") LocalDate date) throws NoAnalisesFoundException {
        List<AnaliseMinerioDto> analises = service.getAllAnalisesMinerios(date);
        return ResponseEntity.ok(analises);
     }

    @GetMapping("/pesquisar-lote")
    public ResponseEntity<?> getMineriosPorNomeELote(@RequestParam("lote") String lote) {
        List<CadastrarMineriosDTO> lista = service.returnPesquisaLote(lote);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(lista);
    }

}
