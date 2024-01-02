package com.brayan_lipe.examen3.domain.ports.out;

import com.brayan_lipe.examen3.domain.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoOut {
    List<Producto> getAll();
    Producto create(Producto producto);
    Optional<Producto> getById(Long id);
    Optional<Producto> updateById(Long id, Producto producto);
    boolean deleteById(Long id);
}
