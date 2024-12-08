package com.newnocturnalhunter.api_rest.controller;

import com.newnocturnalhunter.api_rest.dto.PartidasDTO;
import com.newnocturnalhunter.api_rest.exceptions.*;
import com.newnocturnalhunter.api_rest.service.PartidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidas") // -> http://localhost:8080/partidas
public class PartidasController {
    @Autowired
    private PartidasService partidasService;

    @GetMapping("/") // -> http://localhost:8080/partidas/
    public ResponseEntity<?> getAllPartidas() {
        try{
            List<PartidasDTO> partidasDTO = partidasService.getAllPartidas();
            return new ResponseEntity<>(partidasDTO, HttpStatus.OK);
        }catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/");
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/{idPartida}") // -> http://localhost:8080/partidas/
    public ResponseEntity<?> getPartidaById(@PathVariable String idPartida) {
        try{
            PartidasDTO partidaDTO = partidasService.getPartidaById(idPartida);
            return new ResponseEntity<>(partidaDTO, HttpStatus.OK);
        }catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping("/") // -> http://localhost:8080/partidas/
    public ResponseEntity<?> createPartida(@RequestBody PartidasDTO partidaDTO) {
        try {
            partidasService.createPartida(partidaDTO);
            return new ResponseEntity<>(partidaDTO, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/");
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/{idPartida}") // -> http://localhost:8080/partidas/
    public ResponseEntity<?> updatePartida(@PathVariable String idPartida, @RequestBody PartidasDTO partidaDTO) {
        try {
            partidasService.updatePartida(idPartida, partidaDTO);
            return new ResponseEntity<>(partidaDTO, HttpStatus.OK);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/{idPartida}") // -> http://localhost:8080/partidas/
    public ResponseEntity<?> deletePartida(@PathVariable String idPartida) {
        try {
            partidasService.deletePartida(idPartida);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/partidas/" + idPartida);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
