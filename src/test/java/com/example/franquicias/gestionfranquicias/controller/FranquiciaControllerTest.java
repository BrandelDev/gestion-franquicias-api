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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.franquicias.gestionfranquicias.dto.response.FranquiciaResponse;
import com.example.franquicias.gestionfranquicias.service.FranquiciaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class FranquiciaControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private FranquiciaService franquiciaService;

    @InjectMocks
    private FranquiciaController franquiciaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(franquiciaController).build();
    }

    @Test
    void crearFranquicia_retornaResponse() throws Exception {
        FranquiciaResponse response = FranquiciaResponse.builder()
                .id(1L)
                .nombre("Franquicia 1")
                .build();

        when(franquiciaService.crearFranquicia(org.mockito.ArgumentMatchers.any()))
                .thenReturn(response);

        com.example.franquicias.gestionfranquicias.dto.request.CreateFranquiciaRequest request =
                new com.example.franquicias.gestionfranquicias.dto.request.CreateFranquiciaRequest();
        request.setNombre("Franquicia 1");

        String body = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/franquicias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Franquicia 1"));
    }
}
