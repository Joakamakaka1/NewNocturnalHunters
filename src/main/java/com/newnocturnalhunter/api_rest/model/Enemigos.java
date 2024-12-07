package com.newnocturnalhunter.api_rest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "enemigos")
public class Enemigos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    private Integer estadisticas;
    @Enumerated(EnumType.STRING)
    private TipoEnemigo tipo;
    private String imagen;

    public enum TipoEnemigo {
        Basico, Intermedio, Boss
    }
}
