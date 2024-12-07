package com.newnocturnalhunter.api_rest.dto;

import com.newnocturnalhunter.api_rest.model.Cliente;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonajesDTO {
    private String nombre;
    private String estadisticas;
    private Integer tipo;
    private String imagen;
    private Cliente cliente;
}
