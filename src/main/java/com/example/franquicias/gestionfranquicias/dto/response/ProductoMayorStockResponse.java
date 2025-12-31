package com.example.franquicias.gestionfranquicias.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductoMayorStockResponse {
    private String sucursal;
    private String producto;
    private Integer stock;
}
