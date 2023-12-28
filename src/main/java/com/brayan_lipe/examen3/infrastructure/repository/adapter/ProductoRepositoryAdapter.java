package com.brayan_lipe.examen3.infrastructure.repository.adapter;

import com.brayan_lipe.examen3.domain.model.Producto;
import com.brayan_lipe.examen3.domain.ports.out.ProductoOut;
import com.brayan_lipe.examen3.infrastructure.entity.ProductoEntity;
import com.brayan_lipe.examen3.infrastructure.repository.ProductoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductoRepositoryAdapter implements ProductoOut {
    private final ProductoRepository productoRepository;

    public ProductoRepositoryAdapter(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto create(Producto producto) {
        ProductoEntity productoEntity = ProductoEntity.fromDomainModel(producto);
        return productoRepository.save(productoEntity).toDomainModel();
    }

    @Override
    public Optional<Producto> getById(Long id) {
        return productoRepository.existsById(id)
                ? productoRepository.findById(id).map(ProductoEntity::toDomainModel) : Optional.empty();
    }

    @Override
    public Optional<Producto> updateById(Long id, Producto producto) {
        ProductoEntity productoEntity = ProductoEntity.fromDomainModel(producto);
        return productoRepository.existsById(id) ? Optional.of(productoRepository.save(productoEntity).toDomainModel()) : Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
