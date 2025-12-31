package com.example.franquicias.gestionfranquicias.service;

import org.springframework.stereotype.Service;

import com.example.franquicias.gestionfranquicias.model.Sucursal;
import com.example.franquicias.gestionfranquicias.repository.FranquiciaRepository;
import com.example.franquicias.gestionfranquicias.repository.SucursalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SucursalService {

    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;

    public Sucursal crearSucursal(Long franquiciaId, String nombreSucursal) {
        var franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new IllegalArgumentException("La franquicia no existe"));

        if (sucursalRepository.existsByNombreAndFranquiciaId(nombreSucursal, franquiciaId)) {
            throw new IllegalArgumentException("La sucursal ya existe en la franquicia");
        }

        var sucursal = com.example.franquicias.gestionfranquicias.model.Sucursal.builder()
                .nombre(nombreSucursal)
                .franquicia(franquicia)
                .build();
        return sucursalRepository.save(sucursal);
    }


}
