package com.example.franquicias.gestionfranquicias.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "franquicia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Franquicia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "franquicia", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Sucursal> sucursales;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

}
