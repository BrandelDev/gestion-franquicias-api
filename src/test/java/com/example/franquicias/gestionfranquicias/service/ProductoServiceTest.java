package com.example.franquicias.gestionfranquicias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.franquicias.gestionfranquicias.dto.response.ProductoMayorStockResponse;
import com.example.franquicias.gestionfranquicias.model.Producto;
import com.example.franquicias.gestionfranquicias.model.Sucursal;
import com.example.franquicias.gestionfranquicias.repository.ProductoRepository;
import com.example.franquicias.gestionfranquicias.repository.SucursalRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void obtenerProductosMayorStockPorFranquicia_devuelveListado() {
        Long franquiciaId = 1L;
        Sucursal sucursal = Sucursal.builder()
                .id(10L)
                .nombre("Sucursal 1")
                .build();
        Producto producto = Producto.builder()
                .id(20L)
                .nombre("Producto A")
                .stock(15)
                .sucursal(sucursal)
                .build();

        when(sucursalRepository.findByFranquiciaId(franquiciaId)).thenReturn(List.of(sucursal));
        when(productoRepository.findProductosWithHighestStockInSucursal(sucursal.getId()))
                .thenReturn(List.of(producto));

        List<ProductoMayorStockResponse> response =
                productoService.obtenerProductosMayorStockPorFranquicia(franquiciaId);

        assertEquals(1, response.size());
        assertEquals("Sucursal 1", response.get(0).getSucursal());
        assertEquals("Producto A", response.get(0).getProducto());
        assertEquals(15, response.get(0).getStock());
    }

    @Test
    void obtenerProductosMayorStockPorFranquicia_sinSucursales_lanzaError() {
        Long franquiciaId = 2L;
        when(sucursalRepository.findByFranquiciaId(franquiciaId)).thenReturn(List.of());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> productoService.obtenerProductosMayorStockPorFranquicia(franquiciaId));

        assertEquals("La franquicia no tiene sucursales", ex.getMessage());
    }
}
