package com.newnocturnalhunter.api_rest.service;

import com.newnocturnalhunter.api_rest.dto.ClienteDTO;
import com.newnocturnalhunter.api_rest.dto.ClienteRegisterDTO;
import com.newnocturnalhunter.api_rest.exceptions.BadRequestException;
import com.newnocturnalhunter.api_rest.exceptions.DuplicateException;
import com.newnocturnalhunter.api_rest.exceptions.NotFoundException;
import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.repository.ClienteRepository;
import com.newnocturnalhunter.api_rest.utils.Mapper;
import com.newnocturnalhunter.api_rest.utils.StringToLong;
import com.newnocturnalhunter.api_rest.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Cliente service.
 */
@Service
public class ClienteService implements UserDetailsService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Validator validator;
    @Autowired
    private Mapper mapper;
    @Autowired
    private StringToLong stringToLong;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario No encontrado"));


        UserDetails userDetails = User
                .builder()
                .username(cliente.getUsername())
                .password(cliente.getPassword())
                .roles(cliente.getRoles().split(","))
                .build();

        return userDetails;
    }

    /**
     * Create cliente register dto.
     *
     * @param clienteRegisterDTO the cliente register dto
     * @return the cliente register dto
     */
    public ClienteRegisterDTO create(ClienteRegisterDTO clienteRegisterDTO) {
        if (clienteRepository.findByUsername(clienteRegisterDTO.getUsername()).isPresent()) {
            throw new DuplicateException("El usuario ya existe");
        }

        if (!clienteRegisterDTO.getPassword1().equals(clienteRegisterDTO.getPassword2())) {
            throw new BadRequestException("Las contraseñas no coninciden");
        }

        if (!validator.validateRole(clienteRegisterDTO.getRoles())) {
            throw new BadRequestException("El rol tiene que ser USER o ADMIN");
        }

        if (!validator.validateUsername(clienteRegisterDTO.getUsername())) {
            throw new BadRequestException("El username debe tener al menos 3 caracteres");
        }

        if (!validator.validatePassword(clienteRegisterDTO.getPassword1())) {
            throw new BadRequestException("La contraseñas debe tener al menos 6 caracteres");
        }

        if (!validator.validateEmail(clienteRegisterDTO.getEmail())) {
            throw new BadRequestException("El email no es valido");
        }

        Cliente cliente = new Cliente();
        cliente.setUsername(clienteRegisterDTO.getUsername());
        cliente.setPassword(passwordEncoder.encode(clienteRegisterDTO.getPassword1()));
        cliente.setEmail(clienteRegisterDTO.getEmail());
        cliente.setRoles(clienteRegisterDTO.getRoles());

        clienteRepository.save(cliente);
        return clienteRegisterDTO;
    }

    /**
     * Find by nombre cliente dto.
     *
     * @param username the username
     * @return the cliente dto
     */
    public ClienteDTO findByNombre(String username) {
        Cliente u = clienteRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuario con nombre " + username + " no encontrado"));

        return mapper.mapToClienteDTO(u);
    }

    /**
     * Gets all clientes.
     *
     * @return the all clientes
     */
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new NotFoundException("No hay clientes registrados.");
        }

        List<ClienteDTO> clientesDTO = new ArrayList<>();
        clientes.forEach(cliente -> clientesDTO.add(mapper.mapToClienteDTO(cliente)));
        return clientesDTO;
    }

    /**
     * Update cliente dto.
     *
     * @param username   the username
     * @param clienteDTO the cliente dto
     * @return the cliente dto
     */
    public ClienteDTO update(String username, ClienteDTO clienteDTO) {
        if (!validator.validateUsername(username)) {
            throw new BadRequestException("El username no puede estar vacío.");
        }

        if (!validator.validateRole(clienteDTO.getRoles())) {
            throw new BadRequestException("El rol tiene que ser USER o ADMIN");
        }

        if (!validator.validateUsername(clienteDTO.getUsername())) {
            throw new BadRequestException("El username debe tener al menos 3 caracteres");
        }

        if (!validator.validatePassword(clienteDTO.getPassword())) {
            throw new BadRequestException("La contraseñas debe tener al menos 6 caracteres");
        }

        if (!validator.validateEmail(clienteDTO.getEmail())) {
            throw new BadRequestException("El email no es valido");
        }

        Cliente cliente = mapper.mapToCliente(clienteDTO);
        cliente.setUsername(clienteDTO.getUsername());
        cliente.setPassword(passwordEncoder.encode(clienteDTO.getPassword()));
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setRoles(clienteDTO.getRoles());

        clienteRepository.save(cliente);
        return mapper.mapToClienteDTO(cliente);
    }

    /**
     * Delete.
     *
     * @param userName the user name
     */
    public void delete(String userName) {
        if (userName == null || userName.isEmpty() || userName.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        Cliente cliente = clienteRepository.findByUsername(userName)
                .orElseThrow(() -> new NotFoundException("El cliente con el username proporcionado no existe."));

        clienteRepository.delete(cliente);
    }
}
