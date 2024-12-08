package com.newnocturnalhunter.api_rest.utils;

import com.newnocturnalhunter.api_rest.dto.EnemigosDTO;
import com.newnocturnalhunter.api_rest.dto.PartidasDTO;
import com.newnocturnalhunter.api_rest.dto.PersonajesDTO;
import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.model.Enemigos;
import com.newnocturnalhunter.api_rest.model.Partidas;
import com.newnocturnalhunter.api_rest.model.Personajes;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    // Partidas
    public PartidasDTO mapToPartidasDTO(Partidas partidas) {
        PartidasDTO partidasDTO = new PartidasDTO();
        partidasDTO.setResultado(partidas.getResultado());
        partidasDTO.setDuracion(partidas.getDuracion());
        partidasDTO.setFechaInicio(partidas.getFechaInicio());
        partidasDTO.setId_cliente(partidas.getCliente().getId());
        return partidasDTO;
    }

    public Partidas mapToPartidas(PartidasDTO partidasDTO, Cliente cliente) {
        Partidas partidas = new Partidas();
        partidas.setResultado(partidasDTO.getResultado());
        partidas.setDuracion(partidasDTO.getDuracion());
        partidas.setFechaInicio(partidasDTO.getFechaInicio());
        partidas.setCliente(cliente);
        return partidas;
    }

    // Personajes
    public PersonajesDTO mapToPersonajesDTO(Personajes personaje) {
        PersonajesDTO personajeDTO = new PersonajesDTO();
        personajeDTO.setNombre(personaje.getNombre());
        personajeDTO.setVelocidad(personaje.getVelocidad());
        personajeDTO.setVida(personaje.getVida());
        personajeDTO.setSalud(personaje.getSalud());
        personajeDTO.setDamage(personaje.getDamage());
        personajeDTO.setTipo(personaje.getTipo());
        personajeDTO.setImagen(personaje.getImagen());
        personajeDTO.setId_cliente(personaje.getCliente().getId());
        return personajeDTO;
    }

    public Personajes mapToPersonaje(PersonajesDTO personajeDTO, Cliente cliente) {
        Personajes personaje = new Personajes();
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setVelocidad(personajeDTO.getVelocidad());
        personaje.setVida(personajeDTO.getVida());
        personaje.setSalud(personajeDTO.getSalud());
        personaje.setDamage(personajeDTO.getDamage());
        personaje.setTipo(personajeDTO.getTipo());
        personaje.setImagen(personajeDTO.getImagen());
        personaje.setCliente(cliente);
        return personaje;
    }

    // Enemigos
    public EnemigosDTO mapToEnemigosDTO(Enemigos enemigo) {
        EnemigosDTO enemigoDTO = new EnemigosDTO();
        enemigoDTO.setNombre(enemigo.getNombre());
        enemigoDTO.setVelocidad(enemigo.getVelocidad());
        enemigoDTO.setVida(enemigo.getVida());
        enemigoDTO.setDamage(enemigo.getDamage());
        enemigoDTO.setTipo(enemigo.getTipo());
        enemigoDTO.setImagen(enemigo.getImagen());
        enemigoDTO.setSalud(enemigo.getSalud());
        return enemigoDTO;
    }

    public Enemigos mapToEnemigo(EnemigosDTO enemigoDTO) {
        Enemigos enemigo = new Enemigos();
        enemigo.setNombre(enemigoDTO.getNombre());
        enemigo.setVelocidad(enemigoDTO.getVelocidad());
        enemigo.setVida(enemigoDTO.getVida());
        enemigo.setDamage(enemigoDTO.getDamage());
        enemigo.setTipo(enemigoDTO.getTipo());
        enemigo.setImagen(enemigoDTO.getImagen());
        enemigo.setSalud(enemigoDTO.getSalud());
        return enemigo;
    }

}
