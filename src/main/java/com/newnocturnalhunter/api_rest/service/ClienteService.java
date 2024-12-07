package com.newnocturnalhunter.api_rest.service;

import com.newnocturnalhunter.api_rest.dto.ClienteRegisterDTO;
import com.newnocturnalhunter.api_rest.exceptions.BadRequestException;
import com.newnocturnalhunter.api_rest.exceptions.DuplicateException;
import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.repository.ClienteRepository;
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

        if(!clienteRegisterDTO.getRol().equals("USER") && !clienteRegisterDTO.getRol().equals("ADMIN")) {
            throw new BadRequestException("El rol tiene ques ser USER o ADMIN");
        }

        if(!clienteRegisterDTO.getPassword1().equals(clienteRegisterDTO.getPassword2())) {
            throw new BadRequestException("Las contraseñas no coninciden");
        }

        if(!validator.validateEmail(clienteRegisterDTO.getEmail())) {
            throw new BadRequestException("El email no es valido");
        }

        Cliente cliente = new Cliente();
        cliente.setUsername(clienteRegisterDTO.getUsername());
        cliente.setPassword(passwordEncoder.encode(clienteRegisterDTO.getPassword1()));
        cliente.setEmail(clienteRegisterDTO.getEmail());
        cliente.setRol(clienteRegisterDTO.getRol());

        clienteRepository.save(cliente);
        return clienteRegisterDTO;
    }
}
