package com.brayan_lipe.examen3.domain.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FacturaCabecera {
    private Long id;
    private String clienteNombre;
    private String clienteNumeroDoc;
    private Date fechaEmision;
    private Double total;
}
