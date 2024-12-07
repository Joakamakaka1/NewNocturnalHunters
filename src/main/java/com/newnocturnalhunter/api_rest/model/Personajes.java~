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
@Table(name = "personajes")
public class Personajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    private Integer estadisticas;
    @Enumerated(EnumType.STRING)
    private TipoPersonaje tipo;
    private String imagen;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public enum TipoPersonaje {
        Espadachin, Disparador, Mele, Tanque
    }
}
