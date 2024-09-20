package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.CadastrarMineriosEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarMineriosDTO {

    private Integer id;
    private String minerio;
    private Double valorTonelada;
    private String lote;
    private String patio;
    private String transportador;
    private Double frete;
    private LocalDate createdAt;

    public CadastrarMineriosDTO(CadastrarMineriosEntity entity) {
        CadastrarMineriosDTO dto = new CadastrarMineriosDTO();
        this.id = entity.getId();
        this.minerio = entity.getMinerio();
        this.valorTonelada = entity.getValorTonelada();
        this.lote = entity.getLote();
        this.patio = entity.getPatio();
        this.transportador = entity.getTransportador();
        this.frete = entity.getFrete();
        this.createdAt = entity.getCreatedAt();
    }
}
