package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridasEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoCorridasFoundException;
import com.citygusa.com.citygusaapi.Service.ControleDeCorridasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/runs")
public class ControleDeCorridasController {

    private static DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Autowired
    private ControleDeCorridasService controleDeCorridasService;

    @ExceptionHandler(NoCorridasFoundException.class)
    public ResponseEntity<String> handleNoCorridasFoundException(NoCorridasFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<ControleDeCorridasDto> saveCorridas(@RequestBody ControleCorridasEntity entity) {
        Optional<ControleDeCorridasDto> savedCorridas = controleDeCorridasService.saveCorridas(entity);
        return savedCorridas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(500).build());
    }

    @GetMapping("/today")
    public ResponseEntity<List<ControleDeCorridasDto>> getAllCorridas() {
        List<ControleDeCorridasDto> corridas = controleDeCorridasService.getAllCorridasToday(LocalDate.now());
        return ResponseEntity.ok(corridas);
    }

    @GetMapping("/por-data")
    public ResponseEntity<List<ControleDeCorridasDto>> getAllCorridasPorData(@RequestParam("date") LocalDate date) {
        List<ControleDeCorridasDto> corridas = controleDeCorridasService.getAllCorridasToday(date);
        return ResponseEntity.ok(corridas);
    }



}
