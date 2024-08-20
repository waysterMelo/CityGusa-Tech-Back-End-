package com.citygusa.com.citygusaapi.Dto;


import com.citygusa.com.citygusaapi.Entity.AnaliseGusa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnaliseGusaDto {

    private Integer id;
    private String data;
    private String produto;
    private String ferro;
    private String silicio;
    private String aluminio;

    public AnaliseGusaDto(AnaliseGusa entity) {
        this.id = entity.getId();
        this.data = entity.getData();
        this.produto = entity.getProduto();
        this.silicio = entity.getSilicio();
        this.ferro = entity.getFerro();
        this.aluminio = entity.getAluminio();
    }
}
