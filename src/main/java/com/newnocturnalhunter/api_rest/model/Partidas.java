package com.newnocturnalhunter.api_rest.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "partidas")
public class Partidas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resultado;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Integer duracion;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String fechaInicio;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
