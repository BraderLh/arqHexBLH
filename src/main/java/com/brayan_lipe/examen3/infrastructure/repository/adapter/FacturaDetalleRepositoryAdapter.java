package com.brayan_lipe.examen3.infrastructure.repository.adapter;

import com.brayan_lipe.examen3.domain.model.FacturaDetalle;
import com.brayan_lipe.examen3.domain.ports.out.FacturaDetalleOut;
import com.brayan_lipe.examen3.infrastructure.entity.FacturaDetalleEntity;
import com.brayan_lipe.examen3.infrastructure.repository.FacturaDetalleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FacturaDetalleRepositoryAdapter implements FacturaDetalleOut {
    private final FacturaDetalleRepository facturaDetalleRepository;

    public FacturaDetalleRepositoryAdapter(FacturaDetalleRepository facturaDetalleRepository) {
        this.facturaDetalleRepository = facturaDetalleRepository;
    }

    @Override
    public FacturaDetalle create(FacturaDetalle facturaDetalle) {
        FacturaDetalleEntity facturaDetalleEntity = FacturaDetalleEntity.fromDomainModel(facturaDetalle);
        return facturaDetalleRepository.save(facturaDetalleEntity).toDomainModel();
    }

    @Override
    public Optional<FacturaDetalle> getById(Long id) {
        return facturaDetalleRepository.existsById(id)
                ? facturaDetalleRepository.findById(id).map(FacturaDetalleEntity::toDomainModel) : Optional.empty();
    }

    @Override
    public Optional<FacturaDetalle> updateById(Long id, FacturaDetalle producto) {
        FacturaDetalleEntity facturaDetalleEntity = FacturaDetalleEntity.fromDomainModel(producto);
        return facturaDetalleRepository.existsById(id) ? Optional.of(facturaDetalleRepository.save(facturaDetalleEntity).toDomainModel()) : Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (facturaDetalleRepository.existsById(id)) {
            facturaDetalleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
