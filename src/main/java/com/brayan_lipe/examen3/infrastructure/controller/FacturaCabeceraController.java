package com.brayan_lipe.examen3.infrastructure.controller;

import com.brayan_lipe.examen3.application.service.FacturaCabeceraService;
import com.brayan_lipe.examen3.application.service.FacturaDetalleService;
import com.brayan_lipe.examen3.domain.model.FacturaCabecera;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/facturacabecera")
public class FacturaCabeceraController {
    private final FacturaCabeceraService facturaCabeceraService;
    private final FacturaDetalleService facturaDetalleService;

    public FacturaCabeceraController(FacturaCabeceraService facturaCabeceraService, FacturaDetalleService facturaDetalleService) {
        this.facturaCabeceraService = facturaCabeceraService;
        this.facturaDetalleService = facturaDetalleService;
    }

    @PostMapping
    public ResponseEntity<FacturaCabecera> createFacturaCabecera(@RequestBody FacturaCabecera facturaCabecera) {
        FacturaCabecera facturaCabecera1 = facturaCabeceraService.create(facturaCabecera);
        return new ResponseEntity<>(facturaCabecera1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FacturaCabecera>> getFacturaCabeceras() {
        return new ResponseEntity<>(facturaCabeceraService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{facturaId}")
    public ResponseEntity<FacturaCabecera> getFacturaCabeceraById(@PathVariable Long facturaId) {
        return facturaCabeceraService.getById(facturaId)
                .map(facturaCabecera -> new ResponseEntity<>(facturaCabecera, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{facturaId}")
    public ResponseEntity<FacturaCabecera> updateFacturaCabecera(@PathVariable Long facturaId, @RequestBody FacturaCabecera facturaCabecera) {
        return facturaCabeceraService.updateById(facturaId, facturaCabecera)
                .map(facturaCabecera1 -> new ResponseEntity<>(facturaCabecera1, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/getTotal/{facturaId}")
    public ResponseEntity<FacturaCabecera> getTotalFacturaCabecera(@PathVariable Long facturaId) {
        Optional<FacturaCabecera> facturaCabecera = facturaCabeceraService.getById(facturaId);
        facturaCabecera.ifPresent(facturaCabecera1 -> {
            facturaCabecera1.setTotal(facturaDetalleService.calculateTotalById(facturaId));
        });
        facturaCabecera.orElseThrow();
        return facturaCabeceraService.updateById(facturaId, facturaCabecera.get())
                .map(facturaCabecera1 -> new ResponseEntity<>(facturaCabecera1, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{facturaId}")
    public ResponseEntity<String> deleteFacturaCabeceraById(@PathVariable Long facturaId) {
        if (facturaCabeceraService.deleteById(facturaId)) {
            return new ResponseEntity<>("Eliminación satisfactoria", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Eliminación fallida", HttpStatus.NOT_FOUND);
        }
    }
}
