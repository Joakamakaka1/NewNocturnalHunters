package com.newnocturnalhunter.api_rest.controller;

import com.newnocturnalhunter.api_rest.dto.PartidasDTO;
import com.newnocturnalhunter.api_rest.service.PartidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Partidas controller.
 */
@RestController
@RequestMapping("/partidas") // -> http://localhost:8080/partidas
public class PartidasController {
    @Autowired
    private PartidasService partidasService;

    /**
     * Gets all partidas.
     *
     * @return the all partidas
     */
    @GetMapping("/") // -> http://localhost:8080/partidas/
    public ResponseEntity<List<PartidasDTO>> getAllPartidas() {
        List<PartidasDTO> partidasDTO = partidasService.getAllPartidas();
        return new ResponseEntity<>(partidasDTO, HttpStatus.OK);
    }

    /**
     * Gets partida by id.
     *
     * @param idPartida the id partida
     * @return the partida by id
     */
    @GetMapping("/{idPartida}") // -> http://localhost:8080/partidas/{idPartida}
    public ResponseEntity<PartidasDTO> getPartidaById(@PathVariable String idPartida) {
        PartidasDTO partidaDTO = partidasService.getPartidaById(idPartida);
        return new ResponseEntity<>(partidaDTO, HttpStatus.OK);
    }

    /**
     * Create partida response entity.
     *
     * @param partidaDTO the partida dto
     * @return the response entity
     */
    @PostMapping("/") // -> http://localhost:8080/partidas/
    public ResponseEntity<PartidasDTO> createPartida(@RequestBody PartidasDTO partidaDTO) {
        partidasService.createPartida(partidaDTO);
        return new ResponseEntity<>(partidaDTO, HttpStatus.CREATED);
    }

    /**
     * Update partida response entity.
     *
     * @param idPartida  the id partida
     * @param partidaDTO the partida dto
     * @return the response entity
     */
    @PutMapping("/{idPartida}") // -> http://localhost:8080/partidas/{idPartida}
    public ResponseEntity<PartidasDTO> updatePartida(@PathVariable String idPartida, @RequestBody PartidasDTO partidaDTO) {
        partidasService.updatePartida(idPartida, partidaDTO);
        return new ResponseEntity<>(partidaDTO, HttpStatus.OK);
    }

    /**
     * Delete partida response entity.
     *
     * @param idPartida the id partida
     * @return the response entity
     */
    @DeleteMapping("/{idPartida}") // -> http://localhost:8080/partidas/{idPartida}
    public ResponseEntity<Void> deletePartida(@PathVariable String idPartida) {
        partidasService.deletePartida(idPartida);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
