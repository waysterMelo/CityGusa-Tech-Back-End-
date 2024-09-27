package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.AnaliseGusaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusaEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Service.IMPL.AnaliseGusaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/analise-gusa")
public class AnaliseGusaController {


    @Autowired
    private AnaliseGusaImpl analiseGusaImpl;

    @PostMapping
    public ResponseEntity<AnaliseGusaDto> salvarAnaliseGusa(@RequestBody AnaliseGusaEntity analiseGusa) {
       return analiseGusaImpl.save(analiseGusa);
    }

    @GetMapping()
    public ResponseEntity<List<AnaliseGusaDto>> listAnaliseGusa() throws NoAnalisesFoundException {
        List<AnaliseGusaDto> lista = analiseGusaImpl.getAllAnalisesGusa(LocalDate.now());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/por-data")
    public ResponseEntity<List<AnaliseGusaDto>> listAnaliseGusaPorData(@RequestParam("data") LocalDate date ) throws NoAnalisesFoundException {
     List<AnaliseGusaDto> lista = analiseGusaImpl.getAllAnalisesGusa(date);
     return ResponseEntity.ok(lista);
    }
}