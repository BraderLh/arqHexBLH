package com.brayan_lipe.examen3.infrastructure.repository.adapter;

import com.brayan_lipe.examen3.domain.model.FacturaCabecera;
import com.brayan_lipe.examen3.domain.ports.out.FacturaCabeceraOut;
import com.brayan_lipe.examen3.infrastructure.entity.FacturaCabeceraEntity;
import com.brayan_lipe.examen3.infrastructure.repository.FacturaCabeceraRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FacturaCabeceraRepositoryAdapter implements FacturaCabeceraOut {
    private final FacturaCabeceraRepository facturaCabeceraRepository;

    public FacturaCabeceraRepositoryAdapter(FacturaCabeceraRepository facturaCabeceraRepository) {
        this.facturaCabeceraRepository = facturaCabeceraRepository;
    }

    @Override
    public List<FacturaCabecera> getAll() {
        List<FacturaCabeceraEntity> facturaCabeceraEntities = facturaCabeceraRepository.findAll();
        return facturaCabeceraEntities.stream().sorted(Comparator.comparing(FacturaCabeceraEntity::getId)).map(
                facturaCabeceraEntity -> new FacturaCabecera(facturaCabeceraEntity.getId(), facturaCabeceraEntity.getClienteNombre(),
                        facturaCabeceraEntity.getClienteNumeroDoc(), facturaCabeceraEntity.getFechaEmision(),
                        facturaCabeceraEntity.getTotal())).collect(Collectors.toList());
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
        if (facturaCabeceraRepository.existsById(id)) {
            Optional<FacturaCabeceraEntity> foundFacturaCabecera = facturaCabeceraRepository.findById(id);
            if (foundFacturaCabecera.isPresent()) {
                FacturaCabeceraEntity facturaCabeceraEntity = FacturaCabeceraEntity.fromDomainModel(facturaCabecera);
                foundFacturaCabecera.get().setClienteNombre(facturaCabeceraEntity.getClienteNombre() == null ||
                        facturaCabeceraEntity.getClienteNombre().trim().isEmpty() ||
                        facturaCabeceraEntity.getClienteNombre().isBlank()
                        ? foundFacturaCabecera.get().getClienteNombre()
                        : facturaCabeceraEntity.getClienteNombre());
                foundFacturaCabecera.get().setClienteNumeroDoc(facturaCabeceraEntity.getClienteNumeroDoc() == null ||
                        facturaCabeceraEntity.getClienteNumeroDoc().trim().isEmpty() ||
                        facturaCabeceraEntity.getClienteNumeroDoc().isBlank()
                        ? foundFacturaCabecera.get().getClienteNumeroDoc()
                        : facturaCabeceraEntity.getClienteNumeroDoc());
                foundFacturaCabecera.get().setFechaEmision(facturaCabeceraEntity.getFechaEmision() == null ?
                        foundFacturaCabecera.get().getFechaEmision() : facturaCabeceraEntity.getFechaEmision());
                foundFacturaCabecera.get().setTotal(facturaCabeceraEntity.getTotal() == null ?
                        foundFacturaCabecera.get().getTotal() : facturaCabeceraEntity.getTotal());
                return Optional.of(facturaCabeceraRepository.save(foundFacturaCabecera.get()).toDomainModel());
            }
        }
        return Optional.empty();
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
