package com.example.franquicias.gestionfranquicias.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.franquicias.gestionfranquicias.dto.response.ProductoMayorStockResponse;
import com.example.franquicias.gestionfranquicias.service.ProductoService;

@ExtendWith(MockitoExtension.class)
class ReactiveProductoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ReactiveProductoController reactiveProductoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reactiveProductoController).build();
    }

    @Test
    void obtenerProductosMayorStockPorFranquicia_retornaListado() throws Exception {
        ProductoMayorStockResponse response = ProductoMayorStockResponse.builder()
                .sucursal("Sucursal 1")
                .producto("Producto R")
                .stock(30)
                .build();

        when(productoService.obtenerProductosMayorStockPorFranquicia(1L))
                .thenReturn(List.of(response));

        MvcResult result = mockMvc.perform(get("/reactive/franquicias/1/productos-mayor-stock"))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sucursal").value("Sucursal 1"))
                .andExpect(jsonPath("$[0].producto").value("Producto R"))
                .andExpect(jsonPath("$[0].stock").value(30));
    }
}
