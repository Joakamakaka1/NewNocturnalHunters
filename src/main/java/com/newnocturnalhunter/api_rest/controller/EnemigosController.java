package com.newnocturnalhunter.api_rest.controller;

import com.newnocturnalhunter.api_rest.dto.EnemigosDTO;
import com.newnocturnalhunter.api_rest.exceptions.*;
import com.newnocturnalhunter.api_rest.service.EnemigosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enemigos") // -> http://localhost:8080/enemigos
public class EnemigosController {
    @Autowired
    private EnemigosService enemigosService;

    @GetMapping("/") // -> http://localhost:8080/enemigos/
    public ResponseEntity <?> getAllEnemigos() {
        try{
            List<EnemigosDTO> enemigosDTO = enemigosService.getAllEnemigos();
            return new ResponseEntity<>(enemigosDTO, HttpStatus.OK);
        }catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/");
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }catch (UnauthorizedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{idEnemigo}") // -> http://localhost:8080/enemigos/
    public ResponseEntity <?> getEnemigoById(@PathVariable String idEnemigo) {
        try{
            EnemigosDTO enemigoDTO = enemigosService.getEnemigoById(idEnemigo);
            return new ResponseEntity<>(enemigoDTO, HttpStatus.OK);
        }catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }catch (UnauthorizedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/") // -> http://localhost:8080/enemigos/
    public ResponseEntity<?> createEnemigo(@RequestBody EnemigosDTO enemigoDTO) {
        try {
            enemigosService.createEnemigo(enemigoDTO);
            return new ResponseEntity<>(enemigoDTO, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/");
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }catch (UnauthorizedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{idEnemigo}") // -> http://localhost:8080/enemigos/
    public ResponseEntity<?> updateEnemigo(@PathVariable String idEnemigo, @RequestBody EnemigosDTO enemigoDTO) {
        try {
            enemigosService.updateEnemigo(idEnemigo, enemigoDTO);
            return new ResponseEntity<>(enemigoDTO, HttpStatus.OK);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }catch (UnauthorizedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{idEnemigo}") // -> http://localhost:8080/enemigos/
    public ResponseEntity<?> deleteEnemigo(@PathVariable String idEnemigo) {
        try {
            enemigosService.deleteEnemigo(idEnemigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }catch (UnauthorizedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/enemigos/" + idEnemigo);
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }
}
