package com.brayan_lipe.examen3.application.usecase;

import com.brayan_lipe.examen3.domain.model.FacturaDetalle;
import com.brayan_lipe.examen3.domain.ports.in.FacturaDetalleIn;
import com.brayan_lipe.examen3.domain.ports.out.FacturaDetalleOut;

import java.util.Optional;

public class FacturaDetalleServiceImpl implements FacturaDetalleIn {
    private final FacturaDetalleOut facturaDetalleOut;

    public FacturaDetalleServiceImpl(FacturaDetalleOut facturaDetalleOut) {
        this.facturaDetalleOut = facturaDetalleOut;
    }

    @Override
    public FacturaDetalle create(FacturaDetalle facturaDetalle) {
        return facturaDetalleOut.create(facturaDetalle);
    }

    @Override
    public Optional<FacturaDetalle> getById(Long id) {
        return facturaDetalleOut.getById(id);
    }

    @Override
    public Optional<FacturaDetalle> updateById(Long id, FacturaDetalle facturaDetalle) {
        return facturaDetalleOut.updateById(id, facturaDetalle);
    }

    @Override
    public boolean deleteById(Long id) {
        return facturaDetalleOut.deleteById(id);
    }
}
