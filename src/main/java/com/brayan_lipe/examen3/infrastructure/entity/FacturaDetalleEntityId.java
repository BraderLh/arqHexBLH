package com.brayan_lipe.examen3.infrastructure.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class FacturaDetalleEntityId implements Serializable {
    private Long facturaCabecera;
    private Long producto;
}
