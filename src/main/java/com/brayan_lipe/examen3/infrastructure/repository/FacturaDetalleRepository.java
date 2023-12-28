package com.brayan_lipe.examen3.infrastructure.repository;

import com.brayan_lipe.examen3.infrastructure.entity.FacturaDetalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaDetalleRepository  extends JpaRepository<FacturaDetalleEntity, Long> {
}
