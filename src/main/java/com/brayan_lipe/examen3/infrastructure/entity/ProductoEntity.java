package com.brayan_lipe.examen3.infrastructure.entity;

import com.brayan_lipe.examen3.domain.model.Producto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "productos")
public class ProductoEntity {
    @Id
    @Column(name = "producto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "stock")
    private int stock;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<FacturaDetalleEntity> facturaDetalleEntitySet = new HashSet<>();

    public ProductoEntity() {
    }

    public ProductoEntity(Long id, String nombre, String descripcion, Double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public static ProductoEntity fromDomainModel(Producto producto) {
        return new ProductoEntity(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock());
    }

    public Producto toDomainModel() {
        return new Producto(id, nombre, descripcion, precio, stock);
    }
}
