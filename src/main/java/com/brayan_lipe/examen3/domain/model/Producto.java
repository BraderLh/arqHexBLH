package com.brayan_lipe.examen3.domain.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
}
