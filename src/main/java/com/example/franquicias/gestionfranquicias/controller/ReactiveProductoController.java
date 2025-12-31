package com.example.franquicias.gestionfranquicias.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.franquicias.gestionfranquicias.dto.response.ProductoMayorStockResponse;
import com.example.franquicias.gestionfranquicias.service.ProductoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/reactive/franquicias")
@RequiredArgsConstructor
public class ReactiveProductoController {

    private final ProductoService productoService;

    @GetMapping("/{franquiciaId}/productos-mayor-stock")
    public Mono<ResponseEntity<List<ProductoMayorStockResponse>>> obtenerProductosMayorStockPorFranquicia(
            @PathVariable Long franquiciaId) {
        return Mono.fromCallable(() -> productoService.obtenerProductosMayorStockPorFranquicia(franquiciaId))
                .map(ResponseEntity::ok)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
