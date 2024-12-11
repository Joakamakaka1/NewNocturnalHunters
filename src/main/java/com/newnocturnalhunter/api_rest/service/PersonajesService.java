package com.newnocturnalhunter.api_rest.service;

import com.newnocturnalhunter.api_rest.dto.PersonajesDTO;
import com.newnocturnalhunter.api_rest.exceptions.BadRequestException;
import com.newnocturnalhunter.api_rest.exceptions.NotFoundException;
import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.model.Personajes;
import com.newnocturnalhunter.api_rest.repository.ClienteRepository;
import com.newnocturnalhunter.api_rest.repository.PersonajesRepository;
import com.newnocturnalhunter.api_rest.utils.Mapper;
import com.newnocturnalhunter.api_rest.utils.StringToLong;
import com.newnocturnalhunter.api_rest.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Personajes service.
 */
@Service
public class PersonajesService {
    @Autowired
    private PersonajesRepository personajesRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private StringToLong stringToLong;
    @Autowired
    private Mapper mapper;
    @Autowired
    private Validator validator;

    /**
     * Gets all personajes.
     *
     * @return the all personajes
     */
    public List<PersonajesDTO> getAllPersonajes() {
        List<Personajes> personajes = personajesRepository.findAll();
        if (personajes.isEmpty()) {
            throw new NotFoundException("No hay personajes registrados.");
        }

        List<PersonajesDTO> personajesDTO = new ArrayList<>();
        personajes.forEach(personaje -> personajesDTO.add(mapper.mapToPersonajesDTO(personaje)));
        return personajesDTO;
    }

    /**
     * Gets personaje by id.
     *
     * @param id the id
     * @return the personaje by id
     */
    public PersonajesDTO getPersonajeById(String id) {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        Personajes personaje = personajesRepository.findById(stringToLong.method(id))
                .orElseThrow(() -> new NotFoundException("El personaje con el ID proporcionado no existe."));
        return mapper.mapToPersonajesDTO(personaje);
    }

    /**
     * Create personaje personajes dto.
     *
     * @param personajesDTO the personajes dto
     * @return the personajes dto
     */
    public PersonajesDTO createPersonaje(PersonajesDTO personajesDTO) {
        if (personajesDTO == null) {
            throw new BadRequestException("El personaje no puede estar vacío.");
        }

        if (!validator.validateTipoPersonaje(personajesDTO.getTipo())) {
            throw new BadRequestException("El tipo de personaje no es válido.");
        }

        Cliente cliente = clienteRepository.findById(personajesDTO.getId_cliente())
                .orElseThrow(() -> new NotFoundException("El cliente asociado al personaje no existe."));

        Personajes personaje = mapper.mapToPersonaje(personajesDTO, cliente);
        personajesRepository.save(personaje);
        return mapper.mapToPersonajesDTO(personaje);
    }

    /**
     * Update personaje personajes dto.
     *
     * @param id            the id
     * @param personajesDTO the personajes dto
     * @return the personajes dto
     */
    public PersonajesDTO updatePersonaje(String id, PersonajesDTO personajesDTO) {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        if (personajesDTO == null) {
            throw new BadRequestException("El personaje no puede estar vacío.");
        }

        if (!validator.validateTipoPersonaje(personajesDTO.getTipo())) {
            throw new BadRequestException("El tipo de personaje no es válido.");
        }

        Cliente cliente = clienteRepository.findById(personajesDTO.getId_cliente())
                .orElseThrow(() -> new NotFoundException("El cliente asociado al personaje no existe."));

        Personajes personajeExistente = personajesRepository.findById(stringToLong.method(id))
                .orElseThrow(() -> new NotFoundException("El personaje con el ID proporcionado no existe."));

        Personajes personaje = mapper.mapToPersonaje(personajesDTO, cliente);
        personaje.setId(personajeExistente.getId());
        personajesRepository.save(personaje);
        return mapper.mapToPersonajesDTO(personaje);
    }

    /**
     * Delete personaje.
     *
     * @param id the id
     */
    public void deletePersonaje(String id) {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        Personajes personaje = personajesRepository.findById(stringToLong.method(id))
                .orElseThrow(() -> new NotFoundException("El personaje con el ID proporcionado no existe."));

        personajesRepository.deleteById(personaje.getId());
    }
}
