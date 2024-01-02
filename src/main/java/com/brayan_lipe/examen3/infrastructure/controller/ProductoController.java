package com.brayan_lipe.examen3.infrastructure.controller;

import com.brayan_lipe.examen3.application.service.ProductoService;
import com.brayan_lipe.examen3.domain.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producto")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        Producto producto1 = productoService.create(producto);
        return new ResponseEntity<>(producto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        List<Producto> productos = productoService.getAll();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long productoId){
        return productoService.getById(productoId)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long productoId, @RequestBody Producto producto) {
        return productoService.updateById(productoId, producto)
                .map(producto1 -> new ResponseEntity<>(producto1, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity<String> deleteProductoById(@PathVariable Long productoId) {
        if (productoService.deleteById(productoId)) {
            return new ResponseEntity<>("Eliminación satisfactoria", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Eliminación fallida", HttpStatus.NOT_FOUND);
        }
    }
}
