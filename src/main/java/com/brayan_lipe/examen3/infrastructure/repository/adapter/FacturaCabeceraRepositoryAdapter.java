package com.brayan_lipe.examen3.infrastructure.repository.adapter;

import com.brayan_lipe.examen3.domain.model.FacturaCabecera;
import com.brayan_lipe.examen3.domain.ports.out.FacturaCabeceraOut;
import com.brayan_lipe.examen3.infrastructure.entity.FacturaCabeceraEntity;
import com.brayan_lipe.examen3.infrastructure.repository.FacturaCabeceraRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FacturaCabeceraRepositoryAdapter implements FacturaCabeceraOut {
    private final FacturaCabeceraRepository facturaCabeceraRepository;

    public FacturaCabeceraRepositoryAdapter(FacturaCabeceraRepository facturaCabeceraRepository) {
        this.facturaCabeceraRepository = facturaCabeceraRepository;
    }


    @Override
    public FacturaCabecera create(FacturaCabecera facturaCabecera) {
        FacturaCabeceraEntity facturaCabeceraEntity = FacturaCabeceraEntity.fromDomainModel(facturaCabecera);
        return facturaCabeceraRepository.save(facturaCabeceraEntity).toDomainModel();
    }

    @Override
    public Optional<FacturaCabecera> getById(Long id) {
        return facturaCabeceraRepository.existsById(id)
                ? facturaCabeceraRepository.findById(id).map(FacturaCabeceraEntity::toDomainModel) : Optional.empty();
    }

    @Override
    public Optional<FacturaCabecera> updateById(Long id, FacturaCabecera facturaCabecera) {
        FacturaCabeceraEntity facturaCabeceraEntity = FacturaCabeceraEntity.fromDomainModel(facturaCabecera);
        return facturaCabeceraRepository.existsById(id) ? Optional.of(facturaCabeceraRepository.save(facturaCabeceraEntity).toDomainModel()) : Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (facturaCabeceraRepository.existsById(id)) {
            facturaCabeceraRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
