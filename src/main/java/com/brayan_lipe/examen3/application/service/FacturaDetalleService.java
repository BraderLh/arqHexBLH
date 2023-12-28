package com.brayan_lipe.examen3.application.service;

import com.brayan_lipe.examen3.domain.model.FacturaDetalle;
import com.brayan_lipe.examen3.domain.ports.in.FacturaDetalleIn;

import java.util.Optional;

public class FacturaDetalleService implements FacturaDetalleIn{
    private final FacturaDetalleIn facturaDetalleIn;

    public FacturaDetalleService(FacturaDetalleIn facturaDetalleIn) {
        this.facturaDetalleIn = facturaDetalleIn;
    }

    @Override
    public FacturaDetalle create(FacturaDetalle facturaDetalle) {
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
}
