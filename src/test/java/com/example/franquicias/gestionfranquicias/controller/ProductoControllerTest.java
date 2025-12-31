package com.example.franquicias.gestionfranquicias.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.franquicias.gestionfranquicias.dto.response.ProductoMayorStockResponse;
import com.example.franquicias.gestionfranquicias.model.Producto;
import com.example.franquicias.gestionfranquicias.service.ProductoService;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }

    @Test
    void crearProducto_retornaProducto() throws Exception {
        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Producto A")
                .stock(5)
                .build();

        when(productoService.crearProducto(1L, "Producto A", 5)).thenReturn(producto);

        mockMvc.perform(post("/productos/sucursal/1")
                        .param("nombre", "Producto A")
                        .param("stock", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Producto A"))
                .andExpect(jsonPath("$.stock").value(5));
    }

    @Test
    void eliminarProducto_retornaNoContent() throws Exception {
        doNothing().when(productoService).eliminarProducto(2L);

        mockMvc.perform(delete("/productos/2"))
                .andExpect(status().isNoContent());
    }

    @Test
    void actualizarStock_retornaProducto() throws Exception {
        Producto producto = Producto.builder()
                .id(3L)
                .nombre("Producto B")
                .stock(10)
                .build();

        when(productoService.actualizarStock(3L, 10)).thenReturn(producto);

        mockMvc.perform(patch("/productos/3/stock")
                        .param("stock", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.stock").value(10));
    }

    @Test
    void obtenerProductosMayorStockPorFranquicia_retornaListado() throws Exception {
        ProductoMayorStockResponse response = ProductoMayorStockResponse.builder()
                .sucursal("Sucursal 1")
                .producto("Producto C")
                .stock(20)
                .build();

        when(productoService.obtenerProductosMayorStockPorFranquicia(4L))
                .thenReturn(List.of(response));

        mockMvc.perform(get("/productos/4/productos-mayor-stock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sucursal").value("Sucursal 1"))
                .andExpect(jsonPath("$[0].producto").value("Producto C"))
                .andExpect(jsonPath("$[0].stock").value(20));
    }
}
