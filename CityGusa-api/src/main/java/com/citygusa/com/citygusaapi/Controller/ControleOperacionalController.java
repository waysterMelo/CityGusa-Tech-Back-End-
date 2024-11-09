package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.ControleOperacionalDto;
import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import com.citygusa.com.citygusaapi.Service.IMPL.ControleOperacionalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/operacional")
public class ControleOperacionalController {


    private final ControleOperacionalImpl service;

    @Autowired
    public ControleOperacionalController(ControleOperacionalImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ControleOperacionalDto> salvar(@RequestBody ControleOperacionalEntity entity) {
        Optional<ControleOperacionalDto> savedInfo = service.save(entity);
        return savedInfo.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


}
