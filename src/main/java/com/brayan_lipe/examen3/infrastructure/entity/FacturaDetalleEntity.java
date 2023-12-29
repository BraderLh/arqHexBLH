package com.brayan_lipe.examen3.infrastructure.entity;

import com.brayan_lipe.examen3.domain.model.FacturaDetalle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(FacturaDetalleEntityId.class)
@Table(name = "facturadetalle")
public class FacturaDetalleEntity {
    @Id
    @Column(name = "detalle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="factura_id", referencedColumnName = "factura_id")
    private FacturaCabeceraEntity facturaCabecera;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    private ProductoEntity producto;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "subtotal")
    private Double subtotal;

    public FacturaDetalleEntity() {
    }

    public FacturaDetalleEntity(Long id, FacturaCabeceraEntity facturaCabecera, ProductoEntity producto, int cantidad, Double precioUnitario, Double subtotal) {
        this.id = id;
        this.facturaCabecera = facturaCabecera;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
    public static FacturaDetalleEntity fromDomainModel(FacturaDetalle facturaDetalle) {
        FacturaCabeceraEntity facturaCabeceraEntity1 = FacturaCabeceraEntity.fromDomainModel(facturaDetalle.getFacturaCabecera());
        ProductoEntity productoEntity1 = ProductoEntity.fromDomainModel(facturaDetalle.getProducto());
        return new FacturaDetalleEntity(facturaDetalle.getId(), facturaCabeceraEntity1, productoEntity1, facturaDetalle.getCantidad(),
                facturaDetalle.getPrecioUnitario(), facturaDetalle.getSubtotal());
    }

    public FacturaDetalle toDomainModel() {
        return new FacturaDetalle(id, facturaCabecera.toDomainModel(), producto.toDomainModel(), cantidad, precioUnitario, subtotal);
    }
}
