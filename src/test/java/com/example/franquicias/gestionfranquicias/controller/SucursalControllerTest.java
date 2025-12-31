package com.example.franquicias.gestionfranquicias.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.franquicias.gestionfranquicias.model.Sucursal;
import com.example.franquicias.gestionfranquicias.service.SucursalService;

@ExtendWith(MockitoExtension.class)
class SucursalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SucursalService sucursalService;

    @InjectMocks
    private SucursalController sucursalController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sucursalController).build();
    }

    @Test
    void crearSucursal_retornaSucursal() throws Exception {
        Sucursal sucursal = Sucursal.builder()
                .id(1L)
                .nombre("Sucursal 1")
                .build();

        when(sucursalService.crearSucursal(1L, "Sucursal 1")).thenReturn(sucursal);

        mockMvc.perform(post("/franquicias/1/sucursales")
                        .param("nombre", "Sucursal 1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Sucursal 1"));
    }
}
