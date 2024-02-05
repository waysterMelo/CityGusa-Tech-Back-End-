package com.citygusa.citygusatech.Controller;

import com.citygusa.citygusatech.Dto.DatasDto;
import com.citygusa.citygusatech.Entity.DatasEntity;
import com.citygusa.citygusatech.Services.DatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/datas")
public class DatasController {

    @Autowired
    private DatasService datasService;

    @PostMapping
    public ResponseEntity<DatasDto> createDatas(@RequestBody DatasDto dto){
        DatasDto result = datasService.insertDatas(dto);
        URI id = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(id).body(result);
    }

}
