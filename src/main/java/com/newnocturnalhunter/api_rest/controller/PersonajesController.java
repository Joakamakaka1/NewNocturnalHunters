package com.newnocturnalhunter.api_rest.controller;

import com.newnocturnalhunter.api_rest.dto.PersonajesDTO;
import com.newnocturnalhunter.api_rest.service.PersonajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Personajes controller.
 */
@RestController
@RequestMapping("/personajes") // -> http://localhost:8080/personajes
public class PersonajesController {
    @Autowired
    private PersonajesService personajesService;

    /**
     * Gets all personajes.
     *
     * @return the all personajes
     */
    @GetMapping("/") // -> http://localhost:8080/personajes/
    public ResponseEntity<List<PersonajesDTO>> getAllPersonajes() {
        List<PersonajesDTO> personajesDTO = personajesService.getAllPersonajes();
        return new ResponseEntity<>(personajesDTO, HttpStatus.OK);
    }

    /**
     * Gets personaje by id.
     *
     * @param idPersonaje the id personaje
     * @return the personaje by id
     */
    @GetMapping("/{idPersonaje}") // -> http://localhost:8080/personajes/{idPersonaje}
    public ResponseEntity<PersonajesDTO> getPersonajeById(@PathVariable String idPersonaje) {
        PersonajesDTO personajeDTO = personajesService.getPersonajeById(idPersonaje);
        return new ResponseEntity<>(personajeDTO, HttpStatus.OK);
    }

    /**
     * Create personaje response entity.
     *
     * @param personajeDTO the personaje dto
     * @return the response entity
     */
    @PostMapping("/") // -> http://localhost:8080/personajes/
    public ResponseEntity<PersonajesDTO> createPersonaje(@RequestBody PersonajesDTO personajeDTO) {
        personajesService.createPersonaje(personajeDTO);
        return new ResponseEntity<>(personajeDTO, HttpStatus.CREATED);
    }

    /**
     * Update personaje response entity.
     *
     * @param idPersonaje  the id personaje
     * @param personajeDTO the personaje dto
     * @return the response entity
     */
    @PutMapping("/{idPersonaje}") // -> http://localhost:8080/personajes/{idPersonaje}
    public ResponseEntity<PersonajesDTO> updatePersonaje(@PathVariable String idPersonaje, @RequestBody PersonajesDTO personajeDTO) {
        personajesService.updatePersonaje(idPersonaje, personajeDTO);
        return new ResponseEntity<>(personajeDTO, HttpStatus.OK);
    }

    /**
     * Delete personaje response entity.
     *
     * @param idPersonaje the id personaje
     * @return the response entity
     */
    @DeleteMapping("/{idPersonaje}") // -> http://localhost:8080/personajes/{idPersonaje}
    public ResponseEntity<Void> deletePersonaje(@PathVariable String idPersonaje) {
        personajesService.deletePersonaje(idPersonaje);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
