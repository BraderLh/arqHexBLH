package com.brayan_lipe.examen3.application.usecase;

import com.brayan_lipe.examen3.domain.model.Producto;
import com.brayan_lipe.examen3.domain.ports.in.ProductoIn;
import com.brayan_lipe.examen3.domain.ports.out.ProductoOut;

import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoIn {
    private final ProductoOut productoOut;

    public ProductoServiceImpl(ProductoOut productoOut) {
        this.productoOut = productoOut;
    }

    @Override
    public List<Producto> getAll() {
        return productoOut.getAll();
    }

    @Override
    public Producto create(Producto producto) {
        return productoOut.create(producto);
    }

    @Override
    public Optional<Producto> getById(Long id) {
        return productoOut.getById(id);
    }

    @Override
    public Optional<Producto> updateById(Long id, Producto producto) {
        return productoOut.updateById(id, producto);
    }

    @Override
    public boolean deleteById(Long id) {
        return productoOut.deleteById(id);
    }
}
