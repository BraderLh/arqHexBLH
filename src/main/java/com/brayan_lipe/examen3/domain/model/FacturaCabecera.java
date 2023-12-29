package com.brayan_lipe.examen3.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FacturaCabecera {
    private Long id;
    private String clienteNombre;
    private String clienteNumeroDoc;
    private Date fechaEmision;
    private Double total;


    public FacturaCabecera(Long id, String clienteNombre, String clienteNumeroDoc, Date fechaEmision, Double total) {
        this.id = id;
        this.clienteNombre = clienteNombre;
        this.clienteNumeroDoc = clienteNumeroDoc;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }
}
