package com.brayan_lipe.examen3.infrastructure.controller;

import com.brayan_lipe.examen3.application.service.FacturaDetalleService;
import com.brayan_lipe.examen3.domain.model.FacturaDetalle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/facturadetalle")
public class FacturaDetalleController {
    private final FacturaDetalleService facturaDetalleService;

    public FacturaDetalleController(FacturaDetalleService facturaDetalleService) {
        this.facturaDetalleService = facturaDetalleService;
    }

    @PostMapping
    public ResponseEntity<FacturaDetalle> createFacturaDetalle(@RequestBody FacturaDetalle facturaDetalle) {
        return new ResponseEntity<>(facturaDetalleService.create(facturaDetalle), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FacturaDetalle>> getFacturaDetalles() {
        return new ResponseEntity<>(facturaDetalleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{facturaId}")
    public ResponseEntity<FacturaDetalle> getFacturaDetalleById(@PathVariable Long facturaId) {
        return facturaDetalleService.getById(facturaId)
                .map(facturaDetalle -> new ResponseEntity<>(facturaDetalle, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{facturaId}")
    public ResponseEntity<FacturaDetalle> updateFacturaDetalleById(@PathVariable Long facturaId, @RequestBody FacturaDetalle facturaDetalle) {
        return facturaDetalleService.updateById(facturaId, facturaDetalle)
                .map(facturaDetalle1 -> new ResponseEntity<>(facturaDetalle1, HttpStatus.ACCEPTED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{facturaId}")
    public ResponseEntity<String> deleteFacturaEntityById(@PathVariable Long facturaId) {
        if (facturaDetalleService.deleteById(facturaId)) {
            return new ResponseEntity<>("Eliminación satisfactoria", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Eliminación fallida", HttpStatus.NOT_FOUND);
        }
    }
}
