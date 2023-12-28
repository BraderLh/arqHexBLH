package com.brayan_lipe.examen3.domain.ports.in;

import com.brayan_lipe.examen3.domain.model.FacturaDetalle;

import java.util.Optional;

public interface FacturaDetalleIn {
    FacturaDetalle create(FacturaDetalle facturaDetalle);
    Optional<FacturaDetalle> getById(Long id);
    Optional<FacturaDetalle> updateById(Long id, FacturaDetalle facturaDetalle);
    boolean deleteById(Long id);
}
