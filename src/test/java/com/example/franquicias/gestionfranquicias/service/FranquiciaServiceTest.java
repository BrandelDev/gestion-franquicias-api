package com.example.franquicias.gestionfranquicias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.franquicias.gestionfranquicias.dto.request.CreateFranquiciaRequest;
import com.example.franquicias.gestionfranquicias.dto.response.FranquiciaResponse;
import com.example.franquicias.gestionfranquicias.model.Franquicia;
import com.example.franquicias.gestionfranquicias.repository.FranquiciaRepository;

@ExtendWith(MockitoExtension.class)
class FranquiciaServiceTest {

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @InjectMocks
    private FranquiciaService franquiciaService;

    @Test
    void crearFranquicia_guardaYRetornaResponse() {
        CreateFranquiciaRequest request = new CreateFranquiciaRequest();
        request.setNombre("Franquicia X");

        when(franquiciaRepository.existsByNombre(request.getNombre())).thenReturn(false);
        when(franquiciaRepository.save(org.mockito.ArgumentMatchers.any(Franquicia.class)))
                .thenAnswer(invocation -> {
                    Franquicia franquicia = invocation.getArgument(0);
                    franquicia.setId(1L);
                    return franquicia;
                });

        FranquiciaResponse response = franquiciaService.crearFranquicia(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Franquicia X", response.getNombre());
        verify(franquiciaRepository).save(org.mockito.ArgumentMatchers.any(Franquicia.class));
    }

    @Test
    void crearFranquicia_duplicada_lanzaError() {
        CreateFranquiciaRequest request = new CreateFranquiciaRequest();
        request.setNombre("Franquicia Duplicada");

        when(franquiciaRepository.existsByNombre(request.getNombre())).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> franquiciaService.crearFranquicia(request));

        assertEquals("La franquicia ya existe", ex.getMessage());
    }
}
