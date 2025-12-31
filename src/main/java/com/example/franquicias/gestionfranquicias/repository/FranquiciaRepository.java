package com.example.franquicias.gestionfranquicias.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.franquicias.gestionfranquicias.model.Franquicia;

public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {

    Optional<Franquicia> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

}
