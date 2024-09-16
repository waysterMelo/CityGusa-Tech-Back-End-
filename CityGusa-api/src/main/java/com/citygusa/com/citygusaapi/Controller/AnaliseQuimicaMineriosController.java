package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.AnaliseMinerioDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseMineriosEntity;
import com.citygusa.com.citygusaapi.Service.IMPL.AnaliseMinerioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/analise-minerio")
public class AnaliseQuimicaMineriosController {

    @Autowired
    private AnaliseMinerioServiceImpl service;

    @PostMapping
    public ResponseEntity<AnaliseMinerioDto> salvarAnaliseMinerio(@RequestBody AnaliseMineriosEntity analiseQuimicaMineriosEntity) {
        return service.save(analiseQuimicaMineriosEntity);
    }
}
