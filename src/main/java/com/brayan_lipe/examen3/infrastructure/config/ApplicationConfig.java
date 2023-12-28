package com.brayan_lipe.examen3.infrastructure.config;

import com.brayan_lipe.examen3.application.service.FacturaCabeceraService;
import com.brayan_lipe.examen3.application.service.FacturaDetalleService;
import com.brayan_lipe.examen3.application.service.ProductoService;
import com.brayan_lipe.examen3.application.usecase.FacturaCabeceraServiceImpl;
import com.brayan_lipe.examen3.application.usecase.FacturaDetalleServiceImpl;
import com.brayan_lipe.examen3.application.usecase.ProductoServiceImpl;
import com.brayan_lipe.examen3.domain.ports.out.FacturaCabeceraOut;
import com.brayan_lipe.examen3.domain.ports.out.FacturaDetalleOut;
import com.brayan_lipe.examen3.domain.ports.out.ProductoOut;
import com.brayan_lipe.examen3.infrastructure.repository.adapter.FacturaCabeceraRepositoryAdapter;
import com.brayan_lipe.examen3.infrastructure.repository.adapter.FacturaDetalleRepositoryAdapter;
import com.brayan_lipe.examen3.infrastructure.repository.adapter.ProductoRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public FacturaCabeceraService facturaCabeceraService(FacturaCabeceraOut facturaCabeceraOut) {
        return new FacturaCabeceraService(new FacturaCabeceraServiceImpl(facturaCabeceraOut));
    }

    @Bean
    public FacturaCabeceraOut facturaCabeceraOut(FacturaCabeceraRepositoryAdapter facturaCabeceraRepositoryAdapter) {
        return facturaCabeceraRepositoryAdapter;
    }

    @Bean
    public ProductoService productoService(ProductoOut productoOut) {
        return new ProductoService(new ProductoServiceImpl(productoOut));
    }

    @Bean
    public ProductoOut productoOut(ProductoRepositoryAdapter productoRepositoryAdapter) {
        return productoRepositoryAdapter;
    }

    @Bean
    public FacturaDetalleService facturaDetalleService(FacturaDetalleOut facturaDetalleOut) {
        return new FacturaDetalleService(new FacturaDetalleServiceImpl(facturaDetalleOut));
    }

    @Bean
    public FacturaDetalleOut facturaDetalleOut(FacturaDetalleRepositoryAdapter facturaDetalleRepositoryAdapter) {
        return facturaDetalleRepositoryAdapter;
    }
}
