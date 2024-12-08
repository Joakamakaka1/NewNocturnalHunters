package com.newnocturnalhunter.api_rest.service;

import com.newnocturnalhunter.api_rest.dto.EnemigosDTO;
import com.newnocturnalhunter.api_rest.exceptions.BadRequestException;
import com.newnocturnalhunter.api_rest.exceptions.GenericException;
import com.newnocturnalhunter.api_rest.exceptions.NotFoundException;
import com.newnocturnalhunter.api_rest.exceptions.ValidationException;
import com.newnocturnalhunter.api_rest.model.Enemigos;
import com.newnocturnalhunter.api_rest.repository.ClienteRepository;
import com.newnocturnalhunter.api_rest.repository.EnemigosRepository;
import com.newnocturnalhunter.api_rest.utils.Mapper;
import com.newnocturnalhunter.api_rest.utils.StringToLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<EnemigosDTO> getAllEnemigos() {
        try {
            List<Enemigos> enemigos = enemigosRepository.findAll();
            if (enemigos.isEmpty()) {
                throw new NotFoundException("No hay enemigos registrados.");
            }

            List<EnemigosDTO> enemigosDTO = new ArrayList<>();
            enemigos.forEach(enemigo -> enemigosDTO.add(mapper.mapToEnemigosDTO(enemigo)));
            return enemigosDTO;
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al obtener la lista de enemigos." + ex.getMessage());
        }
    }

    public EnemigosDTO getEnemigoById(String id) {
        try {
            if (id == null || id.isEmpty() || id.isBlank()) {
                throw new BadRequestException("El ID no puede estar vacío.");
            }

            Enemigos enemigo = enemigosRepository.findById(stringToLong.method(id))
                    .orElseThrow(() -> new NotFoundException("El enemigo con el ID proporcionado no existe."));
            return mapper.mapToEnemigosDTO(enemigo);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al obtener el enemigo por ID." + ex.getMessage());
        }
    }

    public EnemigosDTO createEnemigo(EnemigosDTO enemigosDTO) {
        try {
            if (enemigosDTO == null) {
                throw new ValidationException("El enemigo no puede estar vacío.");
            }

            // TODO validator
            Enemigos enemigo = mapper.mapToEnemigo(enemigosDTO);
            enemigosRepository.save(enemigo);
            return mapper.mapToEnemigosDTO(enemigo);
        } catch (ValidationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al crear el enemigo." + ex.getMessage());
        }
    }

    public EnemigosDTO updateEnemigo(String id, EnemigosDTO enemigosDTO) {
        try {
            if (id == null || id.isEmpty() || id.isBlank()) {
                throw new BadRequestException("El ID no puede estar vacío.");
            }

            if (enemigosDTO == null) {
                throw new ValidationException("El enemigo no puede estar vacío.");
            }

            Enemigos enemigoExistente = enemigosRepository.findById(stringToLong.method(id))
                    .orElseThrow(() -> new NotFoundException("El enemigo con el ID proporcionado no existe."));

            // TODO validator
            Enemigos enemigo = mapper.mapToEnemigo(enemigosDTO);
            enemigo.setId(enemigoExistente.getId());
            enemigosRepository.save(enemigo);
            return mapper.mapToEnemigosDTO(enemigo);
        } catch (BadRequestException | NotFoundException | ValidationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al actualizar el enemigo." + ex.getMessage());
        }
    }

    public void deleteEnemigo(String id) {
        try {
            if (id == null || id.isEmpty() || id.isBlank()) {
                throw new BadRequestException("El ID no puede estar vacío.");
            }

            Enemigos enemigo = enemigosRepository.findById(stringToLong.method(id))
                    .orElseThrow(() -> new NotFoundException("El enemigo con el ID proporcionado no existe."));

            enemigosRepository.delete(enemigo);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al eliminar el enemigo." + ex.getMessage());
        }
    }
}
