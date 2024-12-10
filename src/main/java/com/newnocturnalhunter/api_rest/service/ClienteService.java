package com.newnocturnalhunter.api_rest.service;

import com.newnocturnalhunter.api_rest.dto.ClienteDTO;
import com.newnocturnalhunter.api_rest.dto.ClienteRegisterDTO;
import com.newnocturnalhunter.api_rest.exceptions.BadRequestException;
import com.newnocturnalhunter.api_rest.exceptions.DuplicateException;
import com.newnocturnalhunter.api_rest.exceptions.GenericException;
import com.newnocturnalhunter.api_rest.exceptions.NotFoundException;
import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.repository.ClienteRepository;
import com.newnocturnalhunter.api_rest.utils.Mapper;
import com.newnocturnalhunter.api_rest.utils.StringToLong;
import com.newnocturnalhunter.api_rest.utils.Validator;
import org.hibernate.jpa.event.internal.CallbackDefinitionResolverLegacyImpl;
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
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> authorities = Arrays.stream(cliente.getRol().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                .collect(Collectors.toList());

        UserDetails userDetails = User // User pertenece a SpringSecurity
                .builder()
                .username(cliente.getUsername())
                .password(cliente.getPassword())
                .authorities(authorities)
                .build();

        return userDetails;
    }

    public ClienteRegisterDTO create(ClienteRegisterDTO clienteRegisterDTO) {
        if(clienteRepository.findByUsername(clienteRegisterDTO.getUsername()).isPresent()) {
            throw new DuplicateException("El usuario ya existe");
        }

        if(!clienteRegisterDTO.getPassword1().equals(clienteRegisterDTO.getPassword2())) {
            throw new BadRequestException("Las contraseñas no coninciden");
        }

        if (!validator.validateRole(clienteRegisterDTO.getRol())) {
            throw new BadRequestException("El rol tiene que ser USER o ADMIN");
        }

        if(!validator.validateUsername(clienteRegisterDTO.getUsername())) {
            throw new BadRequestException("El username debe tener al menos 3 caracteres");
        }

        if(!validator.validatePassword(clienteRegisterDTO.getPassword1())) {
            throw new BadRequestException("La contraseñas debe tener al menos 6 caracteres");
        }

        if(!validator.validateEmail(clienteRegisterDTO.getEmail())) {
            throw new BadRequestException("El email no es valido");
        }

        try{
            Cliente cliente = new Cliente();
            cliente.setUsername(clienteRegisterDTO.getUsername());
            cliente.setPassword(passwordEncoder.encode(clienteRegisterDTO.getPassword1()));
            cliente.setEmail(clienteRegisterDTO.getEmail());
            cliente.setRol(clienteRegisterDTO.getRol());

            clienteRepository.save(cliente);
            return clienteRegisterDTO;
        }catch (DuplicateException | BadRequestException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al crear el cliente." + ex.getMessage());
        }
    }

    public ClienteDTO findByNombre(String username) {
        try{
            Cliente u = clienteRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new NotFoundException("Usuario con nombre "+ username +" no encontrado"));

            return mapper.mapToClienteDTO(u);
        }catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al obtener el cliente por nombre." + ex.getMessage());
        }
    }

    public List<ClienteDTO> getAllClientes() {
        try{
            List<Cliente> clientes = clienteRepository.findAll();
            if (clientes.isEmpty()) {
                throw new NotFoundException("No hay clientes registrados.");
            }

            List<ClienteDTO> clientesDTO = new ArrayList<>();
            clientes.forEach(cliente -> clientesDTO.add(mapper.mapToClienteDTO(cliente)));
            return clientesDTO;
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al obtener la lista de enemigos." + ex.getMessage());
        }
    }

    public ClienteDTO update(String username, ClienteDTO clienteDTO) {
        if(!validator.validateUsername(username)) {
            throw new BadRequestException("El username no puede estar vacío.");
        }

        if(!validator.validateRole(clienteDTO.getRol())) {
            throw new BadRequestException("El rol tiene que ser USER o ADMIN");
        }

        if(!validator.validateUsername(clienteDTO.getUsername())) {
            throw new BadRequestException("El username debe tener al menos 3 caracteres");
        }

        if(!validator.validatePassword(clienteDTO.getPassword())) {
            throw new BadRequestException("La contraseñas debe tener al menos 6 caracteres");
        }

        if(!validator.validateEmail(clienteDTO.getEmail())) {
            throw new BadRequestException("El email no es valido");
        }

        try{
            Cliente cliente = mapper.mapToCliente(clienteDTO);
            cliente.setUsername(clienteDTO.getUsername());
            cliente.setPassword(passwordEncoder.encode(clienteDTO.getPassword()));
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setRol(clienteDTO.getRol());

            clienteRepository.save(cliente);
            return mapper.mapToClienteDTO(cliente);
        }catch (BadRequestException | NotFoundException ex) {
            throw ex;
        }catch (Exception ex) {
            throw new GenericException("Error al actualizar el cliente." + ex.getMessage());
        }
    }

    public void delete(String userName) {
        if (userName == null || userName.isEmpty() || userName.isBlank()) {
            throw new BadRequestException("El ID no puede estar vacío.");
        }

        try {
            Cliente cliente = clienteRepository.findByUsername(userName)
                    .orElseThrow(() -> new NotFoundException("El cliente con el ID proporcionado no existe."));

            clienteRepository.delete(cliente);
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericException("Error al eliminar el cliente." + ex.getMessage());
        }
    }
}
