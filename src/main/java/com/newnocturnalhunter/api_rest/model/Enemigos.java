package com.newnocturnalhunter.api_rest.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * The type Enemigos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enemigos")
public class Enemigos {
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
    private TipoEnemigo tipo;
    private String imagen;

    /**
     * The enum Tipo enemigo.
     */
    public enum TipoEnemigo {
        /**
         * Basico tipo enemigo.
         */
        Basico,
        /**
         * Intermedio tipo enemigo.
         */
        Intermedio,
        /**
         * Boss tipo enemigo.
         */
        Boss
    }
}
