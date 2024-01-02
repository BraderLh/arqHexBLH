package com.brayan_lipe.examen3.infrastructure.controller;

import com.brayan_lipe.examen3.application.service.FacturaCabeceraService;
import com.brayan_lipe.examen3.domain.model.FacturaCabecera;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/facturacabecera")
public class FacturaCabeceraController {
    private final FacturaCabeceraService facturaCabeceraService;

    public FacturaCabeceraController(FacturaCabeceraService facturaCabeceraService) {
        this.facturaCabeceraService = facturaCabeceraService;
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

    @DeleteMapping("/{facturaId}")
    public ResponseEntity<String> deleteFacturaCabeceraById(@PathVariable Long facturaId) {
        if (facturaCabeceraService.deleteById(facturaId)) {
            return new ResponseEntity<>("Eliminación satisfactoria", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Eliminación fallida", HttpStatus.NOT_FOUND);
        }
    }
}
