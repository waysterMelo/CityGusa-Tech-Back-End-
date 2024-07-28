package com.citygusa.com.citygusaapi.Dto;

import com.citygusa.com.citygusaapi.Entity.ControleCorridas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControleDeCorridasDto {

    private Long id;
    private String horaInicio;
    private String horaFim;
    private Integer minutos;
    private Integer conchas;
    private Integer silicio_visual;
    private Integer silicio_real;
    private String fosforo;
    private String manganes;
    private String silica;
    private String escoria_inicio;
    private String escoria_fim;
    private String tipo_escoria;
    private Integer carga_fundida_de;
    private Integer carga_fundida_ate;
    private Integer quantidade;
    private Integer fe_gusa_kg;
    private BigDecimal ferro;
    private BigDecimal realTn;
    private BigDecimal tempo_corrida;
    private BigDecimal gusa_minuto;
    private Integer carvao_kg;
    private BigDecimal carvao_metros;
    private Integer sopradores_1;
    private Integer sopradores_2;
    private Integer sopradores_3;
    private Integer sopradores_4;
    private Integer sopradores_5;
    private LocalDateTime createdAt;

    public ControleDeCorridasDto(ControleCorridas entity) {
        // Construtor que aceita uma entidade ControleCorridas
        this.id = entity.getId();
        this.horaInicio = entity.getHoraInicio();
        this.horaFim = entity.getHoraFim();
        this.minutos = entity.getMinutos();
        this.conchas = entity.getConchas();
        this.silicio_visual = entity.getSilicio_visual();
        this.silicio_real = entity.getSilicio_real();
        this.fosforo = entity.getFosforo();
        this.manganes = entity.getManganes();
        this.silica = entity.getSilica();
        this.escoria_inicio = entity.getEscoria_inicio();
        this.escoria_fim = entity.getEscoria_fim();
        this.tipo_escoria = entity.getTipo_escoria();
        this.carga_fundida_de = entity.getCarga_fundida_de();
        this.carga_fundida_ate = entity.getCarga_fundida_ate();
        this.quantidade = entity.getQuantidade();
        this.fe_gusa_kg = entity.getFe_gusa_kg();
        this.ferro = entity.getFerro();
        this.realTn = entity.getRealTn();
        this.tempo_corrida = entity.getTempo_corrida();
        this.gusa_minuto = entity.getGusa_minuto();
        this.carvao_kg = entity.getCarvao_kg();
        this.carvao_metros = entity.getCarvao_metros();
        this.sopradores_1 = entity.getSopradores_1();
        this.sopradores_2 = entity.getSopradores_2();
        this.sopradores_3 = entity.getSopradores_3();
        this.sopradores_4 = entity.getSopradores_4();
        this.sopradores_5 = entity.getSopradores_5();
        this.createdAt = entity.getCreatedAt();
    }

}