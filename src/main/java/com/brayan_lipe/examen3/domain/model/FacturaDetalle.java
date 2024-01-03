package com.brayan_lipe.examen3.domain.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacturaDetalle {
    private Long id;
    private FacturaCabecera facturaCabecera;
    private Producto productos;
    private int cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public Double calcularSubtotal(double cantidad, double precioUnitario) {
        return cantidad*precioUnitario;
    }
}
