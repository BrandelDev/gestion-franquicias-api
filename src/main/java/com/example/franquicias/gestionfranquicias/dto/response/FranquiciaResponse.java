package com.example.franquicias.gestionfranquicias.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FranquiciaResponse {
    private Long id;
    private String nombre;
}
