package com.example.franquicias.gestionfranquicias.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestionFranquiciasOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion Franquicias API")
                        .description("API para gestion de franquicias, sucursales y productos")
                        .version("v1"))
                .addServersItem(new Server().url("/"));
    }
}
