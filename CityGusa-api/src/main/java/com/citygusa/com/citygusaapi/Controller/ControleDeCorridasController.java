package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.ControleDeCorridasDto;
import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import com.citygusa.com.citygusaapi.Service.ControleDeCorridasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/corridas")
public class ControleDeCorridasController {

    @Autowired
    private ControleDeCorridasService controleDeCorridasService;

    @PostMapping
    public ResponseEntity<ControleDeCorridasDto> saveCorridas(@RequestBody ControleCorridas entity) {

        Optional<ControleDeCorridasDto> savedCorridas = controleDeCorridasService.saveCorridas(entity);

        return savedCorridas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(500).build());

    }
}
