package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import com.citygusa.com.citygusaapi.Service.ControleDeCorridasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/corridas")
public class ControleDeCorridasController {

    @Autowired
    private ControleDeCorridasService controleDeCorridasService;

    @PostMapping("/add")
    public ResponseEntity<ControleDeCorridasDto> saveCorridas(@RequestBody ControleCorridas entity) {
        Optional<ControleDeCorridasDto> savedCorridas = controleDeCorridasService.saveCorridas(entity);
        return savedCorridas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(500).build());
    }

    @GetMapping("/allresults")
    public List<ControleDeCorridasDto> getAllCorridas(ControleCorridas entity) {
        return controleDeCorridasService.getAllCorridas(entity);
    }
}
