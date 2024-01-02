package com.brayan_lipe.examen3.infrastructure.repository.adapter;

import com.brayan_lipe.examen3.domain.model.Producto;
import com.brayan_lipe.examen3.domain.ports.out.ProductoOut;
import com.brayan_lipe.examen3.infrastructure.entity.ProductoEntity;
import com.brayan_lipe.examen3.infrastructure.repository.ProductoRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductoRepositoryAdapter implements ProductoOut {
    private final ProductoRepository productoRepository;

    public ProductoRepositoryAdapter(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getAll() {
        List<ProductoEntity> productoEntities = productoRepository.findAll();
        return productoEntities.stream().sorted(Comparator.comparing(ProductoEntity::getId))
                .map(productoEntity -> new Producto(productoEntity.getId(),
                productoEntity.getNombre(), productoEntity.getDescripcion(), productoEntity.getPrecio(),
                productoEntity.getStock())).collect(Collectors.toList());
    }

    @Override
    public Producto create(Producto producto) {
        ProductoEntity productoEntity = ProductoEntity.fromDomainModel(producto);
        return productoRepository.save(productoEntity).toDomainModel();
    }

    @Override
    public Optional<Producto> getById(Long id) {
        return productoRepository.existsById(id) ?
                productoRepository.findById(id).map(ProductoEntity::toDomainModel) : Optional.empty();
    }

    @Override
    public Optional<Producto> updateById(Long id, Producto producto) {
        ProductoEntity productoEntity = ProductoEntity.fromDomainModel(producto);
        if (productoRepository.existsById(id)) {
            Optional<ProductoEntity> foundProductoEntity = productoRepository.findById(id);
            if (foundProductoEntity.isPresent()) {
                foundProductoEntity.get().setNombre(productoEntity.getNombre() == null ||
                        productoEntity.getNombre().trim().isEmpty() ||
                        productoEntity.getNombre().isBlank() ?
                        foundProductoEntity.get().getNombre() :
                        productoEntity.getNombre());
                foundProductoEntity.get().setDescripcion(productoEntity.getDescripcion() == null ||
                        productoEntity.getDescripcion().trim().isEmpty() ||
                        productoEntity.getDescripcion().isBlank() ?
                        foundProductoEntity.get().getDescripcion() :
                        productoEntity.getNombre());
                foundProductoEntity.get().setPrecio(productoEntity.getPrecio() == null ?
                        foundProductoEntity.get().getPrecio() :
                        productoEntity.getPrecio());
                foundProductoEntity.get().setStock(productoEntity.getStock() == null ?
                        foundProductoEntity.get().getStock() :
                        productoEntity.getStock());
                return Optional.of(productoRepository.save(foundProductoEntity.get()).toDomainModel());
            }
        }
        return Optional.empty();
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
