package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.AnaliseGusaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import com.citygusa.com.citygusaapi.Exceptions.NoAnalisesFoundException;
import com.citygusa.com.citygusaapi.Service.AnaliseGusaService;
import com.citygusa.com.citygusaapi.Service.IMPL.AnaliseGusaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/analise-gusa")
public class AnaliseGusaController {

    @Autowired
    private AnaliseGusaService analiseGusaService;

    @Autowired
    private AnaliseGusaImpl analiseGusaImpl;

    @PostMapping
    public ResponseEntity<ResponseEntity<AnaliseGusaDto>> salvarAnaliseGusa(@RequestBody AnaliseGusa analiseGusa) {
         try {
             ResponseEntity<AnaliseGusaDto> rs = analiseGusaService.save(analiseGusa);
             return new ResponseEntity<>(rs, HttpStatus.CREATED);
         }catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }

    }

    @GetMapping()
    public ResponseEntity<List<AnaliseGusaDto>> listAnaliseGusa() throws NoAnalisesFoundException {
        List<AnaliseGusaDto> lista = analiseGusaImpl.getAllAnalisesGusa(LocalDate.now());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/por-data")
    public ResponseEntity listAnaliseGusaPorData(@RequestParam("data") LocalDate date ) throws NoAnalisesFoundException {
     List<AnaliseGusaDto> lista = analiseGusaImpl.getAllAnalisesGusa(date);
     return ResponseEntity.ok(lista);
    }
}
