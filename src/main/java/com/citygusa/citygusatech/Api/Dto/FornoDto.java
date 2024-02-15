package com.citygusa.citygusatech.Api.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;
import com.citygusa.citygusatech.Api.Entity.DataEntity;
import com.citygusa.citygusatech.Api.Entity.FornoEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornoDto {

    private String gaiola;
    private String aMudarDepois;
    private int carga;
    private int acumuloSeco;
    private int cargaHhora;
    private int acumuloCarga;
    private int mediaHora;
    private int rt;
    private Set<DataEntity> data = new HashSet<>();

    public FornoDto(FornoEntity entity) {
        this.gaiola = entity.getGaiola();
        this.aMudarDepois = entity.getAMudarDepois();
        this.carga = entity.getCarga();
        this.acumuloSeco = entity.getAcumuloSeco();
        this.cargaHhora = entity.getCargaHhora();
        this.acumuloCarga = entity.getAcumuloCarga();
        this.mediaHora = entity.getMediaHora();
        this.rt = entity.getRt();
      //  entity.getData().forEach(data -> this.data.add(new DataEntity(data)));
    }
}
