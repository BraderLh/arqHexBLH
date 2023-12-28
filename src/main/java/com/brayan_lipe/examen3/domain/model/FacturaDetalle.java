package com.brayan_lipe.examen3.domain.model;

import com.brayan_lipe.examen3.infrastructure.entity.FacturaCabeceraEntity;
import com.brayan_lipe.examen3.infrastructure.entity.ProductoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FacturaDetalle {
    private Long id;
    private FacturaCabecera facturaCabecera;
    private Producto producto;
    private int cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public FacturaDetalle(Long id, FacturaCabecera facturaCabecera, Producto producto, int cantidad, Double precioUnitario, Double subtotal) {
        this.id = id;
        this.facturaCabecera = facturaCabecera;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
}
