package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.AnaliseMinerioDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseMineriosEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Service.IMPL.AnaliseMinerioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/analise-minerio")
public class AnaliseQuimicaMineriosController {

    @Autowired
    private AnaliseMinerioServiceImpl service;

    @PostMapping
    public ResponseEntity<AnaliseMinerioDto> salvarAnaliseMinerio(@RequestBody AnaliseMineriosEntity analiseQuimicaMineriosEntity) {
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
}
