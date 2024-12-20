package com.newnocturnalhunter.api_rest.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * The type Personajes.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personajes")
public class Personajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    private Integer vida;
    private Integer salud;
    private double damage;
    private double velocidad;
    @Enumerated(EnumType.STRING)
    private TipoPersonaje tipo;
    private String imagen;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    /**
     * The enum Tipo personaje.
     */
    public enum TipoPersonaje {
        /**
         * Espadachin tipo personaje.
         */
        Espadachin,
        /**
         * Disparador tipo personaje.
         */
        Disparador,
        /**
         * Mele tipo personaje.
         */
        Mele,
        /**
         * Tanque tipo personaje.
         */
        Tanque
    }
}
