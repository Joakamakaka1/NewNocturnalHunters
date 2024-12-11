package com.newnocturnalhunter.api_rest.controller;

import com.newnocturnalhunter.api_rest.dto.EnemigosDTO;
import com.newnocturnalhunter.api_rest.service.EnemigosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Enemigos controller.
 */
@RestController
@RequestMapping("/enemigos") // -> http://localhost:8080/enemigos
public class EnemigosController {
    @Autowired
    private EnemigosService enemigosService;

    /**
     * Gets all enemigos.
     *
     * @return the all enemigos
     */
    @GetMapping("/") // -> http://localhost:8080/enemigos/
    public ResponseEntity<List<EnemigosDTO>> getAllEnemigos() {
        List<EnemigosDTO> enemigosDTO = enemigosService.getAllEnemigos();
        return new ResponseEntity<>(enemigosDTO, HttpStatus.OK);
    }

    /**
     * Gets enemigo by id.
     *
     * @param idEnemigo the id enemigo
     * @return the enemigo by id
     */
    @GetMapping("/{idEnemigo}") // -> http://localhost:8080/enemigos/{idEnemigo}
    public ResponseEntity<EnemigosDTO> getEnemigoById(@PathVariable String idEnemigo) {
        EnemigosDTO enemigoDTO = enemigosService.getEnemigoById(idEnemigo);
        return new ResponseEntity<>(enemigoDTO, HttpStatus.OK);
    }

    /**
     * Create enemigo response entity.
     *
     * @param enemigoDTO the enemigo dto
     * @return the response entity
     */
    @PostMapping("/") // -> http://localhost:8080/enemigos/
    public ResponseEntity<EnemigosDTO> createEnemigo(@RequestBody EnemigosDTO enemigoDTO) {
        enemigosService.createEnemigo(enemigoDTO);
        return new ResponseEntity<>(enemigoDTO, HttpStatus.CREATED);
    }

    /**
     * Update enemigo response entity.
     *
     * @param idEnemigo  the id enemigo
     * @param enemigoDTO the enemigo dto
     * @return the response entity
     */
    @PutMapping("/{idEnemigo}") // -> http://localhost:8080/enemigos/{idEnemigo}
    public ResponseEntity<EnemigosDTO> updateEnemigo(@PathVariable String idEnemigo, @RequestBody EnemigosDTO enemigoDTO) {
        enemigosService.updateEnemigo(idEnemigo, enemigoDTO);
        return new ResponseEntity<>(enemigoDTO, HttpStatus.OK);
    }

    /**
     * Delete enemigo response entity.
     *
     * @param idEnemigo the id enemigo
     * @return the response entity
     */
    @DeleteMapping("/{idEnemigo}") // -> http://localhost:8080/enemigos/{idEnemigo}
    public ResponseEntity<Void> deleteEnemigo(@PathVariable String idEnemigo) {
        enemigosService.deleteEnemigo(idEnemigo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
