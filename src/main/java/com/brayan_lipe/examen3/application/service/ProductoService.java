package com.brayan_lipe.examen3.application.service;

import com.brayan_lipe.examen3.domain.model.Producto;
import com.brayan_lipe.examen3.domain.ports.in.ProductoIn;

import java.util.Optional;

public class ProductoService implements ProductoIn {
    private  final ProductoIn productoIn;

    public ProductoService(ProductoIn productoIn) {
        this.productoIn = productoIn;
    }

    @Override
    public Producto create(Producto producto) {
        return productoIn.create(producto);
    }

    @Override
    public Optional<Producto> getById(Long id) {
        return productoIn.getById(id);
    }

    @Override
    public Optional<Producto> updateById(Long id, Producto producto) {
        return productoIn.updateById(id, producto);
    }

    @Override
    public boolean deleteById(Long id) {
        return productoIn.deleteById(id);
    }
}
