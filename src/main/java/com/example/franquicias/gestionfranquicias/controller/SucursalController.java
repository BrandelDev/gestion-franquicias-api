package com.example.franquicias.gestionfranquicias.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.franquicias.gestionfranquicias.model.Sucursal;
import com.example.franquicias.gestionfranquicias.service.SucursalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/franquicias/{franquiciaId}/sucursales")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;

    @PostMapping
    public ResponseEntity<Sucursal> crearSucursal(
            @PathVariable Long franquiciaId,
            @RequestParam String nombre) {

        return ResponseEntity.ok(
                sucursalService.crearSucursal(franquiciaId, nombre));
    }

    

}
