package com.newnocturnalhunter.api_rest.service;

import com.newnocturnalhunter.api_rest.dto.PartidasDTO;
import com.newnocturnalhunter.api_rest.exceptions.BadRequestException;
import com.newnocturnalhunter.api_rest.exceptions.NotFoundException;
import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.model.Partidas;
import com.newnocturnalhunter.api_rest.repository.ClienteRepository;
import com.newnocturnalhunter.api_rest.repository.PartidasRepository;
import com.newnocturnalhunter.api_rest.utils.Mapper;
import com.newnocturnalhunter.api_rest.utils.StringToLong;
import com.newnocturnalhunter.api_rest.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Partidas service.
 */
@Service
public class PartidasService {
    @Autowired
    private PartidasRepository partidasRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private StringToLong stringToLong;
    @Autowired
    private Validator validator;

    /**
     * Gets all partidas.
     *
     * @return the all partidas
     */
    public List<PartidasDTO> getAllPartidas() {
        List<Partidas> partidas = partidasRepository.findAll();
        if (partidas.isEmpty()) {
            throw new NotFoundException("No hay partidas registradas.");
        }

        List<PartidasDTO> partidasDTO = new ArrayList<>(); // Crea una lista vacia
        partidas.forEach(partida -> partidasDTO.add(mapper.mapToPartidasDTO(partida))); // Recorre la lista de partidas y las agrega a la lista
        return partidasDTO;
    }

    /**
     * Gets partida by id.
     *
     * @param id the id
     * @return the partida by id
     */
    public PartidasDTO getPartidaById(String id) {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        Partidas partida = partidasRepository.findById(stringToLong.method(id))
                .orElseThrow(() -> new NotFoundException("La partida con el ID proporcionado no existe."));

        return mapper.mapToPartidasDTO(partida);
    }

    /**
     * Create partida partidas dto.
     *
     * @param partidasDTO the partidas dto
     * @return the partidas dto
     */
    public PartidasDTO createPartida(PartidasDTO partidasDTO) {
        // Validaciones
        if (partidasDTO == null) {
            throw new BadRequestException("La partida no puede estar vacía.");
        }

        if (!validator.validateFecha(partidasDTO.getFechaInicio())) {
            throw new BadRequestException("La fecha de inicio no tiene un formato válido. El formato debe ser yyyy-MM-dd HH:mm:ss");
        }

        // Busca el cliente en la base de datos
        Cliente cliente = clienteRepository.findById(partidasDTO.getId_cliente())
                .orElseThrow(() -> new NotFoundException("El cliente asociado no existe."));

        // Crea la partida en la base de datos
        Partidas partida = mapper.mapToPartidas(partidasDTO, cliente); // Mapea la partidaDTO a partida usando el mapper y el cliente asociado
        partidasRepository.save(partida);
        return mapper.mapToPartidasDTO(partida);
    }

    /**
     * Update partida partidas dto.
     *
     * @param id          the id
     * @param partidasDTO the partidas dto
     * @return the partidas dto
     */
    public PartidasDTO updatePartida(String id, PartidasDTO partidasDTO) {
        // Validaciones
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        if (partidasDTO == null) {
            throw new BadRequestException("La partida no puede estar vacía.");
        }

        if (!validator.validateFecha(partidasDTO.getFechaInicio())) {
            throw new BadRequestException("La fecha de inicio no tiene un formato válido. El formato debe ser yyyy-MM-dd HH:mm:ss");
        }

        // Busca el cliente en la base de datos
        Cliente cliente = clienteRepository.findById(partidasDTO.getId_cliente())
                .orElseThrow(() -> new NotFoundException("El cliente asociado no existe."));

        // Actualiza la partida existente en la base de datos
        Partidas partidaExistente = partidasRepository.findById(stringToLong.method(id))
                .orElseThrow(() -> new NotFoundException("La partida con el ID proporcionado no existe."));

        // Crea la partida en la base de datos
        Partidas partida = mapper.mapToPartidas(partidasDTO, cliente); // Mapea la partidaDTO a partida usando el mapper y el cliente asociado
        partida.setId(partidaExistente.getId());
        partidasRepository.save(partida);
        return mapper.mapToPartidasDTO(partida);
    }

    /**
     * Delete partida.
     *
     * @param id the id
     */
    public void deletePartida(String id) {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        Partidas partida = partidasRepository.findById(stringToLong.method(id))
                .orElseThrow(() -> new NotFoundException("La partida con el ID proporcionado no existe."));

        partidasRepository.deleteById(partida.getId());
    }
}
