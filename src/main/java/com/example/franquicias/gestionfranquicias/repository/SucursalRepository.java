package com.example.franquicias.gestionfranquicias.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.franquicias.gestionfranquicias.model.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

    List<Sucursal> findByFranquiciaId(Long franquiciaId);

    Optional<Sucursal> findByNombreAndFranquiciaId(String nombre, Long franquiciaId);

    boolean existByNombreAndFranquiciaId(String nombre, Long franquiciaId);

}
