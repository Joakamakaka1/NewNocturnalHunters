package com.newnocturnalhunter.api_rest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * The type Cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String roles;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true) // Para que se borren las partidas asociadas al cliente
    private List<Partidas> partidas;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true) // Para que se borren las part asociadas al cliente
    private List<Personajes> personajes;
}
