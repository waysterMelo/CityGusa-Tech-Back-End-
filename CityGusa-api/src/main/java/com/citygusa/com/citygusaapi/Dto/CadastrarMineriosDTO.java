package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CadastrarMineriosDTO {

    private Integer id;
    private String minerioNome;
    private Double valorTonelada;
    private String lote;
    private String patio;
    private String transportador;
    private Double frete;
    private LocalDate createdAt;

    public CadastrarMineriosDTO(CadastrarMineriosEntity entity) {
        this.id = entity.getId();
        this.minerioNome = entity.getMinerio();
        this.valorTonelada = entity.getValorTonelada();
        this.lote = entity.getLote();
        this.patio = entity.getPatio();
        this.transportador = entity.getTransportador();
        this.frete = entity.getFrete();
        this.createdAt = entity.getCreatedAt();
    }

}
