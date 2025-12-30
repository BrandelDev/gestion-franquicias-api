package com.example.franquicias.gestionfranquicias.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.franquicias.gestionfranquicias.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findBySucursalId(Long sucursalId);

    Optional<Producto> findByNombreAndSucursalId(String nombre, Long sucursalId);

    @Query("""
            SELECT p FROM producto p WHERE p.sucursal.id = :sucursalId
               AND p.stock = (
               SELECT MAX(p2.stock) FROM producto p2
               WHERE p2.sucursal.id = :sucursalId
               )
            """)
    List<Producto> findProductosWithHighestStockInSucursal(Long sucursalId);

    boolean existsByNombreAndSucursalId(String nombre, Long sucursalId);

}
