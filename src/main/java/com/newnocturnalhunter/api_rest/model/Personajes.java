package com.newnocturnalhunter.api_rest.model;

import jakarta.persistence.*;
import lombok.*;

@Data
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

    public enum TipoPersonaje {
        Espadachin, Disparador, Mele, Tanque
    }
}
