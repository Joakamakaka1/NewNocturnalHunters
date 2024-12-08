package com.newnocturnalhunter.api_rest.service;

import com.newnocturnalhunter.api_rest.dto.PersonajesDTO;
import com.newnocturnalhunter.api_rest.exceptions.BadRequestException;
import com.newnocturnalhunter.api_rest.exceptions.GenericException;
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

    public List<PersonajesDTO> getAllPersonajes() {
        try {
            List<Personajes> personajes = personajesRepository.findAll();
            if (personajes.isEmpty()) {
                throw new NotFoundException("No hay personajes registrados.");
            }

            List<PersonajesDTO> personajesDTO = new ArrayList<>();
            personajes.forEach(personaje -> personajesDTO.add(mapper.mapToPersonajesDTO(personaje)));
            return personajesDTO;
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al obtener la lista de personajes." + ex.getMessage());
        }
    }

    public PersonajesDTO getPersonajeById(String id) {
        try {
            if (id == null || id.isEmpty() || id.isBlank()) {
                throw new BadRequestException("El ID no puede estar vacío.");
            }

            Personajes personaje = personajesRepository.findById(stringToLong.method(id))
                    .orElseThrow(() -> new NotFoundException("El personaje con el ID proporcionado no existe."));
            return mapper.mapToPersonajesDTO(personaje);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al obtener el personaje por ID." + ex.getMessage());
        }
    }

    public PersonajesDTO createPersonaje(PersonajesDTO personajesDTO) {
        try {
            if (personajesDTO == null) {
                throw new BadRequestException("El personaje no puede estar vacío.");
            }

            if(!validator.validateTipoPersonaje(personajesDTO.getTipo())) {
                throw new BadRequestException("El tipo de personaje no es válido.");
            }

            Cliente cliente = clienteRepository.findById(personajesDTO.getId_cliente())
                    .orElseThrow(() -> new NotFoundException("El cliente asociado al personaje no existe."));

            // TODO validator
            Personajes personaje = mapper.mapToPersonaje(personajesDTO, cliente);
            personajesRepository.save(personaje);
            return mapper.mapToPersonajesDTO(personaje);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al crear el personaje." + ex.getMessage());
        }
    }

    public PersonajesDTO updatePersonaje(String id, PersonajesDTO personajesDTO) {
        try {
            if (id == null || id.isEmpty() || id.isBlank()) {
                throw new BadRequestException("El ID no puede estar vacío.");
            }

            if (personajesDTO == null) {
                throw new BadRequestException("El personaje no puede estar vacío.");
            }

            if(!validator.validateTipoPersonaje(personajesDTO.getTipo())) {
                throw new BadRequestException("El tipo de personaje no es válido.");
            }

            Cliente cliente = clienteRepository.findById(personajesDTO.getId_cliente())
                    .orElseThrow(() -> new NotFoundException("El cliente asociado al personaje no existe."));

            Personajes personajeExistente = personajesRepository.findById(stringToLong.method(id))
                    .orElseThrow(() -> new NotFoundException("El personaje con el ID proporcionado no existe."));

            // TODO validator
            Personajes personaje = mapper.mapToPersonaje(personajesDTO, cliente);
            personaje.setId(personajeExistente.getId());
            personajesRepository.save(personaje);
            return mapper.mapToPersonajesDTO(personaje);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al actualizar el personaje." + ex.getMessage());
        }
    }

    public void deletePersonaje(String id) {
        try {
            if (id == null || id.isEmpty() || id.isBlank()) {
                throw new BadRequestException("El ID no puede estar vacío.");
            }

            Personajes personaje = personajesRepository.findById(stringToLong.method(id))
                    .orElseThrow(() -> new NotFoundException("El personaje con el ID proporcionado no existe."));

            personajesRepository.delete(personaje);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al eliminar el personaje." + ex.getMessage());
        }
    }
}
