package com.example.franquicias.gestionfranquicias.controller;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.franquicias.gestionfranquicias.dto.response.ProductoMayorStockResponse;
import com.example.franquicias.gestionfranquicias.model.Producto;
import com.example.franquicias.gestionfranquicias.service.ProductoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/sucursal/{sucursalId}")
    public ResponseEntity<Producto> crearProducto(
            @PathVariable Long sucursalId,
            @RequestParam String nombre,
            @RequestParam Integer stock) {
        return ResponseEntity.ok().body(productoService.crearProducto(sucursalId, nombre, stock));
    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long productoId) {
        productoService.eliminarProducto(productoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productoId}/stock")
    public ResponseEntity<Producto> actualizarStock(@PathVariable Long productoId, @RequestParam Integer stock) {
        return ResponseEntity.ok().body(productoService.actualizarStock(productoId, stock));
    }

    @GetMapping("/{franquiciaId}/productos-mayor-stock")
    public ResponseEntity<List<ProductoMayorStockResponse>> obtenerProductosMayorStockPorFranquicia(
            @PathVariable Long franquiciaId) {

        return ResponseEntity.ok(
                productoService
                        .obtenerProductosMayorStockPorFranquicia(franquiciaId));
    }

}
