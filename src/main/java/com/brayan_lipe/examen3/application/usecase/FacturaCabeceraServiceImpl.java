package com.brayan_lipe.examen3.application.usecase;

import com.brayan_lipe.examen3.domain.model.FacturaCabecera;
import com.brayan_lipe.examen3.domain.ports.in.FacturaCabeceraIn;
import com.brayan_lipe.examen3.domain.ports.out.FacturaCabeceraOut;

import java.util.List;
import java.util.Optional;

public class FacturaCabeceraServiceImpl implements FacturaCabeceraIn {
    private final FacturaCabeceraOut facturaCabeceraOut;

    public FacturaCabeceraServiceImpl(FacturaCabeceraOut facturaCabeceraOut) {
        this.facturaCabeceraOut = facturaCabeceraOut;
    }

    @Override
    public List<FacturaCabecera> getAll() {
        return facturaCabeceraOut.getAll();
    }

    @Override
    public List<FacturaCabecera> getAllById(Long id) {
        return facturaCabeceraOut.getAllById(id);
    }

    @Override
    public FacturaCabecera create(FacturaCabecera facturaCabecera) {
        return facturaCabeceraOut.create(facturaCabecera);
    }

    @Override
    public Optional<FacturaCabecera> getById(Long id) {
        return facturaCabeceraOut.getById(id);
    }

    @Override
    public Optional<FacturaCabecera> updateById(Long id, FacturaCabecera facturaCabecera) {
        return facturaCabeceraOut.updateById(id, facturaCabecera);
    }

    @Override
    public boolean deleteById(Long id) {
        return facturaCabeceraOut.deleteById(id);
    }

    @Override
    public Double calculateTotalById(Long id) {
        return facturaCabeceraOut.calculateTotalById(id);
    }
}
