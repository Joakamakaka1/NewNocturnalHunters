package com.newnocturnalhunter.api_rest.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnemigosDTO {
    private String nombre;
    private String estadisticas;
    private Integer tipo;
    private String imagen;
}
