package com.citygusa.com.citygusaapi.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class RetornarCalculosDoDia {

    private List<ControleOperacionalDto> dados;
    private Double densidadeMediaTotal;
    private BigDecimal umidadeMediaTotal;

}
