package com.citygusa.citygusatech.Controller;

import com.citygusa.citygusatech.Dto.DatasDto;
import com.citygusa.citygusatech.Services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping(value = "/datas")
@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping()
    public ResponseEntity<DatasDto> createData(@RequestBody DatasDto dto){

                DatasDto newDto = dataService.save(dto);
        URI rs = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(rs).body(newDto);
    }


}
