package com.newnocturnalhunter.api_rest.controller;

import com.newnocturnalhunter.api_rest.dto.ClienteDTO;
import com.newnocturnalhunter.api_rest.dto.ClienteLoginDTO;
import com.newnocturnalhunter.api_rest.dto.ClienteRegisterDTO;
import com.newnocturnalhunter.api_rest.service.ClienteService;
import com.newnocturnalhunter.api_rest.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Cliente controller.
 */
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Login string.
     *
     * @param clienteLoginDTO the cliente login dto
     * @return the string
     */
    @PostMapping("/login") // -> http://localhost:8080/cliente/login
    public String login(@RequestBody ClienteLoginDTO clienteLoginDTO) {
        // Autenticamos al usuario con sus credenciales de inicio de sesión (Username y Password)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(clienteLoginDTO.getUsername(), clienteLoginDTO.getPassword())
        );
        return tokenService.generateToken(authentication); // Retornamos el token
    }

    /**
     * Register response entity.
     *
     * @param clienteRegisterDTO the cliente register dto
     * @return the response entity
     */
    @PostMapping("/register") // -> http://localhost:8080/cliente/register
    public ResponseEntity<ClienteRegisterDTO> register(@RequestBody ClienteRegisterDTO clienteRegisterDTO){
        clienteService.create(clienteRegisterDTO);
        return new ResponseEntity<>(clienteRegisterDTO, HttpStatus.CREATED);
    }

    /**
     * Get cliente response entity.
     *
     * @param username the username
     * @return the response entity
     */
    @GetMapping("/{username}") // -> http://localhost:8080/cliente/{username}
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable String username){
        ClienteDTO clienteDTO = clienteService.findByNombre(username);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    /**
     * Gets all clientes.
     *
     * @return the all clientes
     */
    @GetMapping("/") // -> http://localhost:8080/cliente/
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clientesDTO = clienteService.getAllClientes();
        return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
    }

    /**
     * Update response entity.
     *
     * @param username   the username
     * @param clienteDTO the cliente dto
     * @return the response entity
     */
    @PutMapping("/{username}") // -> http://localhost:8080/cliente/{username}
    public ResponseEntity<ClienteDTO> update(@PathVariable String username, @RequestBody ClienteDTO clienteDTO) {
        clienteService.update(username, clienteDTO);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    /**
     * Delete response entity.
     *
     * @param username the username
     * @return the response entity
     */
    @DeleteMapping("/{username}") // -> http://localhost:8080/cliente/{username}
    public ResponseEntity<Void> delete(@PathVariable String username) {
        clienteService.delete(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
