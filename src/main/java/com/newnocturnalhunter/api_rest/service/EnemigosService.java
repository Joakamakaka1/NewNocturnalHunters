package com.newnocturnalhunter.api_rest.service;

import com.newnocturnalhunter.api_rest.dto.EnemigosDTO;
import com.newnocturnalhunter.api_rest.exceptions.BadRequestException;
import com.newnocturnalhunter.api_rest.exceptions.NotFoundException;
import com.newnocturnalhunter.api_rest.model.Enemigos;
import com.newnocturnalhunter.api_rest.repository.ClienteRepository;
import com.newnocturnalhunter.api_rest.repository.EnemigosRepository;
import com.newnocturnalhunter.api_rest.utils.Mapper;
import com.newnocturnalhunter.api_rest.utils.StringToLong;
import com.newnocturnalhunter.api_rest.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Enemigos service.
 */
@Service
public class EnemigosService {
    @Autowired
    private EnemigosRepository enemigosRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private StringToLong stringToLong;
    @Autowired
    private Mapper mapper;
    @Autowired
    private Validator validator;

    /**
     * Gets all enemigos.
     *
     * @return the all enemigos
     */
    public List<EnemigosDTO> getAllEnemigos() {
        List<Enemigos> enemigos = enemigosRepository.findAll();
        if (enemigos.isEmpty()) {
            throw new NotFoundException("No hay enemigos registrados.");
        }

        List<EnemigosDTO> enemigosDTO = new ArrayList<>();
        enemigos.forEach(enemigo -> enemigosDTO.add(mapper.mapToEnemigosDTO(enemigo)));
        return enemigosDTO;
    }

    /**
     * Gets enemigo by id.
     *
     * @param id the id
     * @return the enemigo by id
     */
    public EnemigosDTO getEnemigoById(String id) {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        Enemigos enemigo = enemigosRepository.findById(stringToLong.method(id))
                .orElseThrow(() -> new NotFoundException("El enemigo con el ID proporcionado no existe."));
        return mapper.mapToEnemigosDTO(enemigo);
    }

    /**
     * Create enemigo enemigos dto.
     *
     * @param enemigosDTO the enemigos dto
     * @return the enemigos dto
     */
    public EnemigosDTO createEnemigo(EnemigosDTO enemigosDTO) {
        // Validaciones
        if (enemigosDTO == null) {
            throw new BadRequestException("El enemigo no puede estar vacío.");
        }

        if (!validator.validateTipoEnemigo(enemigosDTO.getTipo())) {
            throw new BadRequestException("El tipo de enemigo no es válido.");
        }

        // Crea el enemigo
        Enemigos enemigo = mapper.mapToEnemigo(enemigosDTO);
        enemigosRepository.save(enemigo);
        return mapper.mapToEnemigosDTO(enemigo);
    }

    /**
     * Update enemigo enemigos dto.
     *
     * @param id          the id
     * @param enemigosDTO the enemigos dto
     * @return the enemigos dto
     */
    public EnemigosDTO updateEnemigo(String id, EnemigosDTO enemigosDTO) {
        // Validaciones
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        if (enemigosDTO == null) {
            throw new BadRequestException("El enemigo no puede estar vacío.");
        }

        if (!validator.validateTipoEnemigo(enemigosDTO.getTipo())) {
            throw new BadRequestException("El tipo de enemigo no es válido.");
        }

        // Actualiza el enemigo existente en la base de datos
        Enemigos enemigoExistente = enemigosRepository.findById(stringToLong.method(id))
                .orElseThrow(() -> new NotFoundException("El enemigo con el ID proporcionado no existe."));

        Enemigos enemigo = mapper.mapToEnemigo(enemigosDTO);
        enemigo.setId(enemigoExistente.getId());
        enemigosRepository.save(enemigo);
        return mapper.mapToEnemigosDTO(enemigo);
    }

    /**
     * Delete enemigo.
     *
     * @param id the id
     */
    public void deleteEnemigo(String id) {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        Enemigos enemigo = enemigosRepository.findById(stringToLong.method(id))
                .orElseThrow(() -> new NotFoundException("El enemigo con el ID proporcionado no existe."));

        enemigosRepository.deleteById(enemigo.getId());
    }
}
