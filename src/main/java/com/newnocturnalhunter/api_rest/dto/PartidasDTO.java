package com.newnocturnalhunter.api_rest.dto;

import com.newnocturnalhunter.api_rest.model.Cliente;
import lombok.*;

/**
 * The type Partidas dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidasDTO {
    private String resultado;
    private String duracion;
    private String fechaInicio;
    private Long id_cliente;
}
