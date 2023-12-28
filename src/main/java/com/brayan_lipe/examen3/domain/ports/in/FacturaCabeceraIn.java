package com.brayan_lipe.examen3.domain.ports.in;

import com.brayan_lipe.examen3.domain.model.FacturaCabecera;

import java.util.Optional;

public interface FacturaCabeceraIn {
    FacturaCabecera create(FacturaCabecera facturaCabecera);
    Optional<FacturaCabecera> getById(Long id);
    Optional<FacturaCabecera> updateById(Long id, FacturaCabecera facturaCabecera);
    boolean deleteById(Long id);
}
