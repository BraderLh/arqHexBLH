package com.brayan_lipe.examen3.infrastructure.entity;

import com.brayan_lipe.examen3.domain.model.FacturaDetalle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(FacturaDetalleEntityId.class)
@Table(name = "facturadetalle")
public class FacturaDetalleEntity {
    @Id
    @Column(name = "detalle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name="factura_id")
    private FacturaCabeceraEntity facturaCabeceraEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity productoEntity;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "subtotal")
    private Double subtotal;

    public FacturaDetalleEntity(Long id, FacturaCabeceraEntity facturaCabeceraEntity, ProductoEntity productoEntity, int cantidad, Double precioUnitario, Double subtotal) {
        this.id = id;
        this.facturaCabeceraEntity = facturaCabeceraEntity;
        this.productoEntity = productoEntity;
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
        return new FacturaDetalle(id, facturaCabeceraEntity.toDomainModel(), productoEntity.toDomainModel(), cantidad, precioUnitario, subtotal);
    }
}
