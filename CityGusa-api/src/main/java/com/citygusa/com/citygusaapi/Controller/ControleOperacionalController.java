package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.ControleOperacionalDto;
import com.citygusa.com.citygusaapi.Dto.RetornarCalculosDoDia;
import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import com.citygusa.com.citygusaapi.Service.Impl.ControleOperacionalImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
    public ResponseEntity<ControleOperacionalDto> salvar(@RequestBody @Valid ControleOperacionalEntity entity) {
        Optional<ControleOperacionalDto> savedInfo = service.save(entity);
        return savedInfo.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/today")
    public ResponseEntity<RetornarCalculosDoDia> getAllInfo(){
        List<ControleOperacionalDto> info = service.getAllDataByDate(LocalDate.now());
        // Verifica se a lista está vazia
        if (info.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foram encontradas informações para hoje");
        }

        // calculaMediaDensidadeTotalDia
         Double densidadeMedia = service.getDensidadeKgMedia(LocalDate.now());

        //calcular umidadeMediaTotalDoDia
        BigDecimal umidadeMedia  = service.getUmidadeMedia(LocalDate.now());

        //criar resposta com os dados e a media
        RetornarCalculosDoDia infoMaisDensidade = new RetornarCalculosDoDia(info, densidadeMedia, umidadeMedia);

        return ResponseEntity.ok(infoMaisDensidade);


    }

    @GetMapping("/por-data")
    public ResponseEntity<List<ControleOperacionalDto>> getAllInfoPorData(@RequestParam("data") LocalDate data){
        List<ControleOperacionalDto> info = service.getAllDataByDate(data);
        // Verifica se a lista está vazia
        if (info.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foram encontradas informações para a data fornecida.");
        }

        return ResponseEntity.ok(info);
    }

}
