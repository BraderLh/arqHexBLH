package com.brayan_lipe.examen3.infrastructure.entity;

import com.brayan_lipe.examen3.domain.model.FacturaCabecera;
import com.brayan_lipe.examen3.domain.model.FacturaDetalle;
import com.brayan_lipe.examen3.domain.model.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "facturadetalle")
public class FacturaDetalleEntity {
    @Id
    @Column(name = "detalle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="factura_id", referencedColumnName = "factura_id")
    private FacturaCabeceraEntity facturaCabeceraEntity;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    private ProductoEntity productoEntity;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "subtotal")
    private Double subtotal;

    public FacturaDetalleEntity() {
    }

    public FacturaDetalleEntity(Long id, int cantidad, Double precioUnitario, Double subtotal) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public static FacturaDetalleEntity fromDomainModel(FacturaDetalle facturaDetalle) {
        FacturaDetalleEntity facturaDetalleEntity = new FacturaDetalleEntity(facturaDetalle.getId(), facturaDetalle.getCantidad(),
                facturaDetalle.getPrecioUnitario(), facturaDetalle.getSubtotal());
        facturaDetalleEntity.setFacturaCabeceraEntity(FacturaCabeceraEntity.fromDomainModel(facturaDetalle.getFacturaCabecera()));
        facturaDetalleEntity.setProductoEntity(ProductoEntity.fromDomainModel(facturaDetalle.getProductos()));
        return facturaDetalleEntity;
    }

    public FacturaDetalle toDomainModel() {
        FacturaCabecera facturaCabecera = this.facturaCabeceraEntity.toDomainModel();
        Producto producto = this.productoEntity.toDomainModel();
        return new FacturaDetalle(id, facturaCabecera, producto, cantidad, precioUnitario, subtotal);
    }
}
