package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.AnaliseEscoriaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseEscoriaEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Service.Impl.AnaliseEscoriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/analise-escoria")
public class AnaliseEscoriaController {


    @Autowired
    private AnaliseEscoriaImpl service;

    @PostMapping
    public ResponseEntity<AnaliseEscoriaDto> salvarAnaliseEntity(@RequestBody AnaliseEscoriaEntity entity) {
       return service.save(entity);
    }

    @GetMapping()
    public ResponseEntity<List<AnaliseEscoriaDto>> listAnaliseEscoria() throws NoAnalisesFoundException {
        List<AnaliseEscoriaDto> lista = service.getAllAnalisesEscoria(LocalDate.now());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/por-data")
    public ResponseEntity<List<AnaliseEscoriaDto>> listAnaliseEscoriaPorData(@RequestParam("data") LocalDate date )
            throws NoAnalisesFoundException {
     List<AnaliseEscoriaDto> lista = service.getAllAnalisesEscoria(date);
     return ResponseEntity.ok(lista);

    }


}