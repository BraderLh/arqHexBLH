package com.brayan_lipe.examen3.domain.ports.out;

import com.brayan_lipe.examen3.domain.model.FacturaCabecera;

import java.util.Optional;

public interface FacturaCabeceraOut {
    FacturaCabecera create(FacturaCabecera facturaCabecera);
    Optional<FacturaCabecera> getById(Long id);
    Optional<FacturaCabecera> updateById(Long id, FacturaCabecera facturaCabecera);
    boolean deleteById(Long id);
}
