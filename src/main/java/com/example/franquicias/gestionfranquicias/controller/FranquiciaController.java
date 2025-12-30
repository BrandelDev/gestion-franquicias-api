package com.example.franquicias.gestionfranquicias.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.franquicias.gestionfranquicias.dto.request.CreateFranquiciaRequest;
import com.example.franquicias.gestionfranquicias.dto.response.FranquiciaResponse;
import com.example.franquicias.gestionfranquicias.service.FranquiciaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/franquicias")
@RequiredArgsConstructor
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    @PostMapping
    public ResponseEntity<FranquiciaResponse> crearFranquicia(
            @Valid @RequestBody CreateFranquiciaRequest request) {

        FranquiciaResponse response = franquiciaService.crearFranquicia(request);

        return ResponseEntity.ok(response);
    }
}
