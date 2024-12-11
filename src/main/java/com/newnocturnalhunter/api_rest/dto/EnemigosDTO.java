package com.newnocturnalhunter.api_rest.dto;

import com.newnocturnalhunter.api_rest.model.Enemigos;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * The type Enemigos dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnemigosDTO {
    private String nombre;
    private Integer vida;
    private Integer salud;
    private double damage;
    private double velocidad;
    @Enumerated(EnumType.STRING)
    private Enemigos.TipoEnemigo tipo;
    private String imagen;
}
