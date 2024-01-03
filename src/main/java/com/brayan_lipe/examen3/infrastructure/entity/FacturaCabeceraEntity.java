package com.brayan_lipe.examen3.infrastructure.entity;

import com.brayan_lipe.examen3.domain.model.FacturaCabecera;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "facturacabecera")
public class FacturaCabeceraEntity {
    @Id
    @Column(name = "factura_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_nombre")
    private String clienteNombre;

    @Column(name = "cliente_num_documento")
    private String clienteNumeroDoc;

    @Column(name = "fecha_emision")
    private Date fechaEmision;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "facturaCabeceraEntity", cascade = CascadeType.ALL)
    private Set<FacturaDetalleEntity> facturaDetalleEntities;

    public FacturaCabeceraEntity() {
    }

    public FacturaCabeceraEntity(Long id, String clienteNombre, String clienteNumeroDoc, Date fechaEmision, Double total) {
        this.id = id;
        this.clienteNombre = clienteNombre;
        this.clienteNumeroDoc = clienteNumeroDoc;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }
    public static FacturaCabeceraEntity fromDomainModel(FacturaCabecera facturaCabecera) {
        return new FacturaCabeceraEntity(facturaCabecera.getId(), facturaCabecera.getClienteNombre(), facturaCabecera.getClienteNumeroDoc(), facturaCabecera.getFechaEmision(), facturaCabecera.getTotal());
    }

    public FacturaCabecera toDomainModel() {
        return new FacturaCabecera(id, clienteNombre, clienteNumeroDoc, fechaEmision, total);
    }
}
