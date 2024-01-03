package com.brayan_lipe.examen3.infrastructure.repository.adapter;

import com.brayan_lipe.examen3.domain.model.FacturaCabecera;
import com.brayan_lipe.examen3.domain.model.FacturaDetalle;
import com.brayan_lipe.examen3.domain.ports.out.FacturaDetalleOut;
import com.brayan_lipe.examen3.infrastructure.entity.FacturaCabeceraEntity;
import com.brayan_lipe.examen3.infrastructure.entity.FacturaDetalleEntity;
import com.brayan_lipe.examen3.infrastructure.entity.ProductoEntity;
import com.brayan_lipe.examen3.infrastructure.repository.FacturaCabeceraRepository;
import com.brayan_lipe.examen3.infrastructure.repository.FacturaDetalleRepository;
import com.brayan_lipe.examen3.infrastructure.repository.ProductoRepository;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Log
public class FacturaDetalleRepositoryAdapter implements FacturaDetalleOut {
    private final FacturaDetalleRepository facturaDetalleRepository;
    private final FacturaCabeceraRepository facturaCabeceraRepository;
    private final ProductoRepository productoRepository;

    public FacturaDetalleRepositoryAdapter(FacturaDetalleRepository facturaDetalleRepository, FacturaCabeceraRepository facturaCabeceraRepository, ProductoRepository productoRepository) {
        this.facturaDetalleRepository = facturaDetalleRepository;
        this.facturaCabeceraRepository = facturaCabeceraRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<FacturaDetalle> getAll() {
        List<FacturaDetalleEntity> facturaDetalleEntities = facturaDetalleRepository.findAll();
        return facturaDetalleEntities.stream().sorted(Comparator.comparing(FacturaDetalleEntity::getId)).map(facturaDetalleEntity -> new FacturaDetalle(
                facturaDetalleEntity.getId(), facturaDetalleEntity.toDomainModel().getFacturaCabecera(), facturaDetalleEntity.toDomainModel().getProductos(), facturaDetalleEntity.getCantidad(),
                facturaDetalleEntity.getPrecioUnitario(), facturaDetalleEntity.getSubtotal())).collect(Collectors.toList());
    }

    @Override
    public FacturaDetalle create(FacturaDetalle facturaDetalle) {
        FacturaDetalleEntity facturaDetalleEntity = FacturaDetalleEntity.fromDomainModel(facturaDetalle);
        Optional<FacturaCabeceraEntity> facturaCabeceraEntity = facturaCabeceraRepository.findById(facturaDetalle.getFacturaCabecera().getId());
        Optional<ProductoEntity> productoEntity = productoRepository.findById(facturaDetalle.getProductos().getId());
        if (facturaCabeceraEntity.isPresent() && productoEntity.isPresent()) {
            facturaDetalleEntity.setFacturaCabeceraEntity(facturaCabeceraEntity.get());
            facturaDetalleEntity.setProductoEntity(productoEntity.get());
        }
        return facturaDetalleRepository.save(facturaDetalleEntity).toDomainModel();
    }

    @Override
    public Optional<FacturaDetalle> getById(Long id) {
        return facturaDetalleRepository.existsById(id)
                ? facturaDetalleRepository.findById(id).map(FacturaDetalleEntity::toDomainModel) : Optional.empty();
    }

    @Override
    public Optional<FacturaDetalle> updateById(Long id, FacturaDetalle facturaDetalle) {
        if (facturaDetalleRepository.existsById(id)) {
            Optional<FacturaDetalleEntity> foundFacturaDetalleEntity = facturaDetalleRepository.findById(id);
            if(foundFacturaDetalleEntity.isPresent()) {
                FacturaDetalleEntity facturaDetalleEntity = FacturaDetalleEntity.fromDomainModel(facturaDetalle);
                foundFacturaDetalleEntity.get().setFacturaCabeceraEntity(
                        facturaDetalleEntity.getFacturaCabeceraEntity() == null ?
                        foundFacturaDetalleEntity.get().getFacturaCabeceraEntity() :
                                facturaDetalleEntity.getFacturaCabeceraEntity());
                foundFacturaDetalleEntity.get().setProductoEntity(facturaDetalleEntity.getProductoEntity() == null ?
                        foundFacturaDetalleEntity.get().getProductoEntity() :
                        facturaDetalleEntity.getProductoEntity());
                foundFacturaDetalleEntity.get().setCantidad(facturaDetalleEntity.getCantidad() == 0 ?
                        foundFacturaDetalleEntity.get().getCantidad() :
                        facturaDetalleEntity.getCantidad());
                foundFacturaDetalleEntity.get().setSubtotal(facturaDetalleEntity.getSubtotal() == null ?
                        foundFacturaDetalleEntity.get().getSubtotal() :
                        facturaDetalleEntity.getSubtotal());
                return Optional.of(facturaDetalleRepository.save(facturaDetalleEntity).toDomainModel());
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (facturaDetalleRepository.existsById(id)) {
            facturaDetalleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<FacturaCabecera> getFacturasCabeceraById(Long id) {
        List<FacturaDetalleEntity> facturaDetalleList = facturaDetalleRepository.findAll();
        List<FacturaCabecera> facturaCabeceras = new ArrayList<>();
        for (FacturaDetalleEntity facturaDetalleEntity : facturaDetalleList) {
            if (facturaDetalleEntity.getFacturaCabeceraEntity().getId().equals(id)) {
                facturaCabeceras.add(facturaDetalleEntity.getFacturaCabeceraEntity().toDomainModel());
            }
        }
        return facturaCabeceras;
    }

    @Override
    public Double calculateTotalById(Long id) {
        double total;
        List<FacturaDetalleEntity> facturaDetalleEntities = facturaDetalleRepository.findAll();
        List<Double> subTotales = new ArrayList<>();
        Optional<FacturaCabeceraEntity> facturaCabeceraEntity = facturaCabeceraRepository.findById(id);
        if (facturaCabeceraEntity.isPresent()) {
            for (FacturaDetalleEntity facturaDetalleEntity : facturaDetalleEntities) {
                if (facturaDetalleEntity.getFacturaCabeceraEntity().getId().equals(id)) {
                    log.info("id: "+ facturaDetalleEntity.getFacturaCabeceraEntity().getId() + " - subtotal: " + facturaDetalleEntity.getSubtotal());
                    subTotales.add(facturaDetalleEntity.getSubtotal());
                }
            }
            total = subTotales.stream().mapToDouble(Double::doubleValue).sum();
            return total;
        } else {
            return null;
        }
    }
}
