package com.newnocturnalhunter.api_rest.utils;

import com.newnocturnalhunter.api_rest.dto.ClienteDTO;
import com.newnocturnalhunter.api_rest.dto.EnemigosDTO;
import com.newnocturnalhunter.api_rest.dto.PartidasDTO;
import com.newnocturnalhunter.api_rest.dto.PersonajesDTO;
import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.model.Enemigos;
import com.newnocturnalhunter.api_rest.model.Partidas;
import com.newnocturnalhunter.api_rest.model.Personajes;
import org.springframework.stereotype.Component;

/**
 * The type Mapper.
 */
@Component
public class Mapper { // Clase que maneja el mapeo de los objetos
    /**
     * Map to cliente dto cliente dto.
     *
     * @param cliente the cliente
     * @return the cliente dto
     */
    public ClienteDTO mapToClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setUsername(cliente.getUsername());
        clienteDTO.setPassword(cliente.getPassword());
        clienteDTO.setRoles(cliente.getRoles());
        return clienteDTO;
    }

    /**
     * Map to cliente cliente.
     *
     * @param clienteDTO the cliente dto
     * @return the cliente
     */
    public Cliente mapToCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setUsername(clienteDTO.getUsername());
        cliente.setPassword(clienteDTO.getPassword());
        cliente.setRoles(clienteDTO.getRoles());
        return cliente;
    }

    /**
     * Map to partidas dto partidas dto.
     *
     * @param partidas the partidas
     * @return the partidas dto
     */
    public PartidasDTO mapToPartidasDTO(Partidas partidas) {
        PartidasDTO partidasDTO = new PartidasDTO();
        partidasDTO.setResultado(partidas.getResultado());
        partidasDTO.setDuracion(partidas.getDuracion());
        partidasDTO.setFechaInicio(partidas.getFechaInicio());
        partidasDTO.setId_cliente(partidas.getCliente().getId());
        return partidasDTO;
    }

    /**
     * Map to partidas partidas.
     *
     * @param partidasDTO the partidas dto
     * @param cliente     the cliente
     * @return the partidas
     */
    public Partidas mapToPartidas(PartidasDTO partidasDTO, Cliente cliente) {
        Partidas partidas = new Partidas();
        partidas.setResultado(partidasDTO.getResultado());
        partidas.setDuracion(partidasDTO.getDuracion());
        partidas.setFechaInicio(partidasDTO.getFechaInicio());
        partidas.setCliente(cliente);
        return partidas;
    }

    /**
     * Map to personajes dto personajes dto.
     *
     * @param personaje the personaje
     * @return the personajes dto
     */
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

    /**
     * Map to personaje personajes.
     *
     * @param personajeDTO the personaje dto
     * @param cliente      the cliente
     * @return the personajes
     */
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

    /**
     * Map to enemigos dto enemigos dto.
     *
     * @param enemigo the enemigo
     * @return the enemigos dto
     */
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

    /**
     * Map to enemigo enemigos.
     *
     * @param enemigoDTO the enemigo dto
     * @return the enemigos
     */
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
