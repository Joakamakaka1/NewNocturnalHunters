package com.newnocturnalhunter.api_rest.controller;

import com.newnocturnalhunter.api_rest.dto.PersonajesDTO;
import com.newnocturnalhunter.api_rest.exceptions.*;
import com.newnocturnalhunter.api_rest.service.PersonajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personajes") // -> http://localhost:8080/personajes
public class PersonajesController {
    @Autowired
    private PersonajesService personajesService;

    @GetMapping("/") // -> http://localhost:8080/personajes/
    public ResponseEntity<?> getAllPersonajes() {
        try{
            List<PersonajesDTO> personajesDTO = personajesService.getAllPersonajes();
            return new ResponseEntity<>(personajesDTO, HttpStatus.OK);
        }catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/");
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/{idPersonaje}") // -> http://localhost:8080/personajes/
    public ResponseEntity<?> getPersonajeById(@PathVariable String idPersonaje) {
        try{
            PersonajesDTO personajeDTO = personajesService.getPersonajeById(idPersonaje);
            return new ResponseEntity<>(personajeDTO, HttpStatus.OK);
        }catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping("/") // -> http://localhost:8080/personajes/
    public ResponseEntity<?> createPersonaje(@RequestBody PersonajesDTO personajeDTO) {
        try {
            personajesService.createPersonaje(personajeDTO);
            return new ResponseEntity<>(personajeDTO, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/");
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/{idPersonaje}") // -> http://localhost:8080/personajes/
    public ResponseEntity<?> updatePersonaje(@PathVariable String idPersonaje, @RequestBody PersonajesDTO personajeDTO) {
        try {
            personajesService.updatePersonaje(idPersonaje, personajeDTO);
            return new ResponseEntity<>(personajeDTO, HttpStatus.OK);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/{idPersonaje}") // -> http://localhost:8080/personajes/
    public ResponseEntity<?> deletePersonaje(@PathVariable String idPersonaje) {
        try {
            personajesService.deletePersonaje(idPersonaje);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/personajes/" + idPersonaje);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
