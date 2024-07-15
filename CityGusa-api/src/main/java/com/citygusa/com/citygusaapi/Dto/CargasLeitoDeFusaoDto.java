package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.CargasLeitoDeFusao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CargasLeitoDeFusaoDto extends CargasLeitoDeFusao {

    private Integer id;
    private String data_atual;
    private String horas;
    private Integer numeroDaCarga;
    private Double porcentagem;
    private String tipoMinerio;
    private Double quantidade;
    private Integer calcareo;
    private Integer bauxita;
    private Integer coque;
    private Integer secas;
    private Integer totalCargas;
    private Integer sucataGusa;
    private Integer sucataAco;


    public CargasLeitoDeFusaoDto(CargasLeitoDeFusao cargasLeitoDeFusao) {
        this.id = cargasLeitoDeFusao.getId();
        this.data_atual = cargasLeitoDeFusao.getData_atual();
        this.horas = cargasLeitoDeFusao.getHoras();
        this.numeroDaCarga = cargasLeitoDeFusao.getNumeroDaCarga();
        this.porcentagem = cargasLeitoDeFusao.getPorcentagem();
        this.tipoMinerio = cargasLeitoDeFusao.getTipoMinerio();
        this.quantidade = cargasLeitoDeFusao.getQuantidade();
        this.calcareo = cargasLeitoDeFusao.getCalcareo();
        this.bauxita = cargasLeitoDeFusao.getBauxita();
        this.coque = cargasLeitoDeFusao.getCoque();
        this.secas = cargasLeitoDeFusao.getSecas();
        this.totalCargas = cargasLeitoDeFusao.getTotalCargas();
        this.sucataGusa = cargasLeitoDeFusao.getSucataGusa();
        this.sucataAco = cargasLeitoDeFusao.getSucataAco();
    }


}
