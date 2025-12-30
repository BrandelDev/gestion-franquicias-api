package com.example.franquicias.gestionfranquicias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.franquicias.gestionfranquicias.dto.request.CreateFranquiciaRequest;
import com.example.franquicias.gestionfranquicias.dto.response.FranquiciaResponse;
import com.example.franquicias.gestionfranquicias.dto.response.ProductoMayorStockResponse;
import com.example.franquicias.gestionfranquicias.model.Franquicia;
import com.example.franquicias.gestionfranquicias.model.Producto;
import com.example.franquicias.gestionfranquicias.model.Sucursal;
import com.example.franquicias.gestionfranquicias.repository.FranquiciaRepository;
import com.example.franquicias.gestionfranquicias.repository.ProductoRepository;
import com.example.franquicias.gestionfranquicias.repository.SucursalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    public FranquiciaResponse crearFranquicia(CreateFranquiciaRequest request) {

        if (franquiciaRepository.existsByNombre(request.getNombre())) {
            throw new IllegalArgumentException("La franquicia ya existe");
        }

        Franquicia franquicia = Franquicia.builder()
                .nombre(request.getNombre())
                .build();

        franquiciaRepository.save(franquicia);

        return FranquiciaResponse.builder()
                .id(franquicia.getId())
                .nombre(franquicia.getNombre())
                .build();
    }


}