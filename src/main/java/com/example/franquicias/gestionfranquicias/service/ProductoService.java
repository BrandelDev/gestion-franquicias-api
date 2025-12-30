package com.example.franquicias.gestionfranquicias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.franquicias.gestionfranquicias.dto.response.ProductoMayorStockResponse;
import com.example.franquicias.gestionfranquicias.model.Franquicia;
import com.example.franquicias.gestionfranquicias.model.Producto;
import com.example.franquicias.gestionfranquicias.model.Sucursal;
import com.example.franquicias.gestionfranquicias.repository.ProductoRepository;
import com.example.franquicias.gestionfranquicias.repository.SucursalRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    @Transactional
    public Producto crearProducto(Long sucursalId, String nombre, Integer stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new IllegalArgumentException("La sucursal no existe"));

        if (productoRepository.existsByNombreAndSucursalId(nombre, sucursalId)) {
            throw new IllegalArgumentException("El producto ya existe en la sucursal");
        }

        Producto producto = Producto.builder()
                .nombre(nombre)
                .stock(stock)
                .sucursal(sucursal)
                .build();
                
        return productoRepository.save(producto);
    }

    @Transactional
    public void eliminarProducto(Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("El producto no existe"));
        productoRepository.delete(producto);
    }

    @Transactional
    public Producto actualizarStock(Long productoId, Integer nuevoStock) {

        if (nuevoStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("El producto no existe"));

        producto.setStock(nuevoStock);
        return productoRepository.save(producto);
    }

    public List<ProductoMayorStockResponse> obtenerProductosMayorStockPorFranquicia(Long franquiciaId) {

        List<Sucursal> sucursales = sucursalRepository.findByFranquiciaId(franquiciaId);

        if (sucursales.isEmpty()) {
            throw new IllegalArgumentException("La franquicia no tiene sucursales");
        }

        return sucursales.stream()
                .flatMap(sucursal -> productoRepository
                        .findProductosWithHighestStockInSucursal(sucursal.getId())
                        .stream()
                        .map(producto -> ProductoMayorStockResponse.builder()
                                .sucursal(sucursal.getNombre())
                                .producto(producto.getNombre())
                                .stock(producto.getStock())
                                .build()))
                .toList();
    }

}
