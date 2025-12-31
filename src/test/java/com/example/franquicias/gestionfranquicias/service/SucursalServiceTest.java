package com.example.franquicias.gestionfranquicias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.franquicias.gestionfranquicias.model.Franquicia;
import com.example.franquicias.gestionfranquicias.model.Sucursal;
import com.example.franquicias.gestionfranquicias.repository.FranquiciaRepository;
import com.example.franquicias.gestionfranquicias.repository.SucursalRepository;

@ExtendWith(MockitoExtension.class)
class SucursalServiceTest {

    @Mock
    private SucursalRepository sucursalRepository;

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @InjectMocks
    private SucursalService sucursalService;

    @Test
    void crearSucursal_guardaNuevaSucursal() {
        Long franquiciaId = 1L;
        String nombreSucursal = "Sucursal A";
        Franquicia franquicia = Franquicia.builder()
                .id(franquiciaId)
                .nombre("Franquicia 1")
                .build();

        when(franquiciaRepository.findById(franquiciaId)).thenReturn(Optional.of(franquicia));
        when(sucursalRepository.existsByNombreAndFranquiciaId(nombreSucursal, franquiciaId))
                .thenReturn(false);
        when(sucursalRepository.save(org.mockito.ArgumentMatchers.any(Sucursal.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Sucursal result = sucursalService.crearSucursal(franquiciaId, nombreSucursal);

        assertNotNull(result);
        assertEquals(nombreSucursal, result.getNombre());
        assertEquals(franquicia, result.getFranquicia());
        verify(sucursalRepository).save(org.mockito.ArgumentMatchers.any(Sucursal.class));
    }

    @Test
    void crearSucursal_sinFranquicia_lanzaError() {
        Long franquiciaId = 2L;
        when(franquiciaRepository.findById(franquiciaId)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> sucursalService.crearSucursal(franquiciaId, "Sucursal X"));

        assertEquals("La franquicia no existe", ex.getMessage());
    }

    @Test
    void crearSucursal_duplicada_lanzaError() {
        Long franquiciaId = 3L;
        String nombreSucursal = "Sucursal D";
        Franquicia franquicia = Franquicia.builder()
                .id(franquiciaId)
                .nombre("Franquicia 3")
                .build();

        when(franquiciaRepository.findById(franquiciaId)).thenReturn(Optional.of(franquicia));
        when(sucursalRepository.existsByNombreAndFranquiciaId(nombreSucursal, franquiciaId))
                .thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> sucursalService.crearSucursal(franquiciaId, nombreSucursal));

        assertEquals("La sucursal ya existe en la franquicia", ex.getMessage());
    }
}
