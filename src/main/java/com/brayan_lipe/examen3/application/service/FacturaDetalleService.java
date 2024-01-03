package com.brayan_lipe.examen3.application.service;

import com.brayan_lipe.examen3.domain.model.FacturaCabecera;
import com.brayan_lipe.examen3.domain.model.FacturaDetalle;
import com.brayan_lipe.examen3.domain.model.Producto;
import com.brayan_lipe.examen3.domain.ports.in.FacturaCabeceraIn;
import com.brayan_lipe.examen3.domain.ports.in.FacturaDetalleIn;
import com.brayan_lipe.examen3.domain.ports.in.ProductoIn;

import java.util.List;
import java.util.Optional;

public class FacturaDetalleService implements FacturaDetalleIn{
    private final FacturaDetalleIn facturaDetalleIn;
    private final FacturaCabeceraIn facturaCabeceraIn;
    private final ProductoIn productoIn;

    public FacturaDetalleService(FacturaDetalleIn facturaDetalleIn, FacturaCabeceraIn facturaCabeceraIn, ProductoIn productoIn) {
        this.facturaDetalleIn = facturaDetalleIn;
        this.facturaCabeceraIn = facturaCabeceraIn;
        this.productoIn = productoIn;
    }

    @Override
    public List<FacturaDetalle> getAll() {
        return facturaDetalleIn.getAll();
    }

    @Override
    public FacturaDetalle create(FacturaDetalle facturaDetalle) {
        Optional<FacturaCabecera> facturaCabecera = facturaCabeceraIn.getById(facturaDetalle.getFacturaCabecera().getId());
        facturaCabecera.ifPresent(facturaDetalle::setFacturaCabecera);
        facturaCabecera.orElseThrow();
        Optional<Producto> producto = productoIn.getById(facturaDetalle.getProductos().getId());
        producto.ifPresent(facturaDetalle::setProductos);
        producto.orElseThrow();
        facturaDetalle.setPrecioUnitario(facturaDetalle.getProductos().getPrecio());
        facturaDetalle.setSubtotal(facturaDetalle.calcularSubtotal(facturaDetalle.getCantidad(), facturaDetalle.getPrecioUnitario()));
        return facturaDetalleIn.create(facturaDetalle);
    }

    @Override
    public Optional<FacturaDetalle> getById(Long id) {
        return facturaDetalleIn.getById(id);
    }

    @Override
    public Optional<FacturaDetalle> updateById(Long id, FacturaDetalle facturaDetalle) {
        return facturaDetalleIn.updateById(id, facturaDetalle);
    }

    @Override
    public boolean deleteById(Long id) {
        return facturaDetalleIn.deleteById(id);
    }

    @Override
    public List<FacturaCabecera> getFacturasCabeceraById(Long id) {
        return facturaDetalleIn.getFacturasCabeceraById(id);
    }

    @Override
    public Double calculateTotalById(Long id) {
        return facturaDetalleIn.calculateTotalById(id);
    }
}
