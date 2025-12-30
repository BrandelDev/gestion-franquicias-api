package com.example.franquicias.gestionfranquicias.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.franquicias.gestionfranquicias.dto.request.CreateFranquiciaRequest;
import com.example.franquicias.gestionfranquicias.dto.response.FranquiciaResponse;
import com.example.franquicias.gestionfranquicias.dto.response.ProductoMayorStockResponse;
import com.example.franquicias.gestionfranquicias.model.Producto;
import com.example.franquicias.gestionfranquicias.service.FranquiciaService;
import com.example.franquicias.gestionfranquicias.service.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/franquicias")
@RequiredArgsConstructor
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    /**
     * Endpoint para crear una nueva franquicia
     */
    @PostMapping
    public ResponseEntity<FranquiciaResponse> crearFranquicia(
            @Valid @RequestBody CreateFranquiciaRequest request) {

        FranquiciaResponse response = franquiciaService.crearFranquicia(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para obtener el producto con mayor stock por sucursal
     * para una franquicia puntual
     */
}
