package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.CargasLeitoDeFusaoDto;
import com.citygusa.com.citygusaapi.Entity.CargasLeitoDeFusao;
import com.citygusa.com.citygusaapi.Service.IMPL.CargasLeitoDeFusaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/leito-fusao")
public class CargasLeitoDeFusaoController {

    @Autowired
    private CargasLeitoDeFusaoImpl service;

    @PostMapping()
    public ResponseEntity<CargasLeitoDeFusaoDto> salvarCargasLeitoDeFusao(@RequestBody CargasLeitoDeFusao cargasLeitoDeFusao) {
        Optional<CargasLeitoDeFusaoDto> savedDto = service.salvarLeito(cargasLeitoDeFusao);
        if (savedDto.isPresent()) {
            return ResponseEntity.ok(savedDto.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
