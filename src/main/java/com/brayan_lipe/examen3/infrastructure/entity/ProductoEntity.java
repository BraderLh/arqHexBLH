package com.brayan_lipe.examen3.infrastructure.entity;

import com.brayan_lipe.examen3.domain.model.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private Integer stock;

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
