package com.newnocturnalhunter.api_rest.dto;

import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.model.Enemigos;
import com.newnocturnalhunter.api_rest.model.Personajes;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * The type Personajes dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajesDTO {
    private String nombre;
    private Integer vida;
    private Integer salud;
    private double damage;
    private double velocidad;
    @Enumerated(EnumType.STRING)
    private Personajes.TipoPersonaje tipo;
    private String imagen;
    private Long id_cliente;
}
