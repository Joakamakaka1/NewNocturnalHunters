package com.newnocturnalhunter.api_rest.service;

import com.newnocturnalhunter.api_rest.dto.PartidasDTO;
import com.newnocturnalhunter.api_rest.exceptions.BadRequestException;
import com.newnocturnalhunter.api_rest.exceptions.GenericException;
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

    public List<PartidasDTO> getAllPartidas() {
        try {
            List<Partidas> partidas = partidasRepository.findAll();
            if (partidas.isEmpty()) {
                throw new NotFoundException("No hay partidas registradas.");
            }

            List<PartidasDTO> partidasDTO = new ArrayList<>();
            partidas.forEach(partida -> partidasDTO.add(mapper.mapToPartidasDTO(partida)));
            return partidasDTO;
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al obtener las partidas." + ex.getMessage());
        }
    }

    public PartidasDTO getPartidaById(String id) {
        try {
            if (id == null || id.isEmpty() || id.isBlank()) {
                throw new BadRequestException("El ID no puede estar vacío.");
            }

            Partidas partida = partidasRepository.findById(stringToLong.method(id))
                    .orElseThrow(() -> new NotFoundException("La partida con el ID proporcionado no existe."));
            return mapper.mapToPartidasDTO(partida);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al obtener las partidas." + ex.getMessage());
        }
    }

    public PartidasDTO createPartida(PartidasDTO partidasDTO) {
        try {
            if (partidasDTO == null) {
                throw new BadRequestException("La partida no puede estar vacía.");
            }

            if (!validator.validateFecha(partidasDTO.getFechaInicio())) {
                throw new BadRequestException("La fecha de inicio no tiene un formato válido. El formato debe ser yyyy-MM-dd HH:mm:ss");
            }

            Cliente cliente = clienteRepository.findById(partidasDTO.getId_cliente())
                    .orElseThrow(() -> new NotFoundException("El cliente asociado no existe."));

            // TODO validator
            Partidas partida = mapper.mapToPartidas(partidasDTO, cliente);
            partidasRepository.save(partida);
            return mapper.mapToPartidasDTO(partida);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al obtener las partidas." + ex.getMessage());
        }
    }

    public PartidasDTO updatePartida(String id, PartidasDTO partidasDTO) {
        try {
            if (id == null || id.isEmpty() || id.isBlank()) {
                throw new BadRequestException("El ID no puede estar vacío.");
            }

            if (partidasDTO == null) {
                throw new BadRequestException("La partida no puede estar vacía.");
            }

            if (!validator.validateFecha(partidasDTO.getFechaInicio())) {
                throw new BadRequestException("La fecha de inicio no tiene un formato válido. El formato debe ser yyyy-MM-dd HH:mm:ss");
            }

            Cliente cliente = clienteRepository.findById(partidasDTO.getId_cliente())
                    .orElseThrow(() -> new NotFoundException("El cliente asociado no existe."));

            Partidas partidaExistente = partidasRepository.findById(stringToLong.method(id))
                    .orElseThrow(() -> new NotFoundException("La partida con el ID proporcionado no existe."));

            // TODO validator
            Partidas partida = mapper.mapToPartidas(partidasDTO, cliente);
            partida.setId(partidaExistente.getId());
            partidasRepository.save(partida);
            return mapper.mapToPartidasDTO(partida);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al obtener las partidas." + ex.getMessage());
        }
    }

    public void deletePartida(String id) {
        try {
            if (id == null || id.isEmpty() || id.isBlank()) {
                throw new BadRequestException("El ID no puede estar vacío.");
            }

            Partidas partida = partidasRepository.findById(stringToLong.method(id))
                    .orElseThrow(() -> new NotFoundException("La partida con el ID proporcionado no existe."));

            partidasRepository.delete(partida);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al eliminar la partida." + ex.getMessage());
        }
    }
}
