package com.brayan_lipe.examen3.domain.ports.out;

import com.brayan_lipe.examen3.domain.model.FacturaDetalle;

import java.util.List;
import java.util.Optional;

public interface FacturaDetalleOut {
    List<FacturaDetalle> getAll();
    FacturaDetalle create(FacturaDetalle facturaDetalle);
    Optional<FacturaDetalle> getById(Long id);
    Optional<FacturaDetalle> updateById(Long id, FacturaDetalle facturaDetalle);
    boolean deleteById(Long id);
}
