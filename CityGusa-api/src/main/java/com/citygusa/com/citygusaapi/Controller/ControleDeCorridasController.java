package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import com.citygusa.com.citygusaapi.Service.ControleDeCorridasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/runs")
public class ControleDeCorridasController {

    @Autowired
    private ControleDeCorridasService controleDeCorridasService;

    @PostMapping("/add")
    public ResponseEntity<ControleDeCorridasDto> saveCorridas(@RequestBody ControleCorridas entity) {
        Optional<ControleDeCorridasDto> savedCorridas = controleDeCorridasService.saveCorridas(entity);
        return savedCorridas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(500).build());
    }

    @GetMapping("/date-today")
    public List<ControleDeCorridasDto> getAllCorridas(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String data) {
        return controleDeCorridasService.getAllCorridasByDate(new ControleCorridas(), data);
    }
}
