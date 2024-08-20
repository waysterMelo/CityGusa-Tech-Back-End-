package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.AnaliseGusaDto;
import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import com.citygusa.com.citygusaapi.Repository.AnaliseGusaRepository;
import com.citygusa.com.citygusaapi.Service.IMPL.AnaliseGusaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/analise-gusa")
public class AnaliseGusaController {

    @Autowired
    private AnaliseGusaRepository analiseGusaRepository;

    @PostMapping
    public ResponseEntity<AnaliseGusa> salvarAnaliseGusa(@RequestBody AnaliseGusa analiseGusa) {
         try {
             AnaliseGusa rs = analiseGusaRepository.save(analiseGusa);
             return new ResponseEntity<>(rs, HttpStatus.CREATED);
         }catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }

    }

}
