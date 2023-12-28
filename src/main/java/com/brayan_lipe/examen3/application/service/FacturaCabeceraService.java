package com.brayan_lipe.examen3.application.service;

import com.brayan_lipe.examen3.domain.model.FacturaCabecera;
import com.brayan_lipe.examen3.domain.ports.in.FacturaCabeceraIn;

import java.util.Optional;

public class FacturaCabeceraService implements FacturaCabeceraIn {
    private final FacturaCabeceraIn facturaCabeceraIn;

    public FacturaCabeceraService(FacturaCabeceraIn facturaCabeceraIn) {
        this.facturaCabeceraIn = facturaCabeceraIn;
    }

    @Override
    public FacturaCabecera create(FacturaCabecera facturaCabecera) {
        FacturaCabecera facturaCabecera1 = facturaCabeceraIn.create(facturaCabecera);
        return facturaCabecera1;
    }

    @Override
    public Optional<FacturaCabecera> getById(Long id) {
        return facturaCabeceraIn.getById(id);
    }

    @Override
    public Optional<FacturaCabecera> updateById(Long id, FacturaCabecera facturaCabecera) {
        return facturaCabeceraIn.updateById(id, facturaCabecera);
    }

    @Override
    public boolean deleteById(Long id) {
        return facturaCabeceraIn.deleteById(id);
    }
}
