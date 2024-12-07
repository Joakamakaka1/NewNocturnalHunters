package com.newnocturnalhunter.api_rest.controller;

import com.newnocturnalhunter.api_rest.dto.ClienteLoginDTO;
import com.newnocturnalhunter.api_rest.dto.ClienteRegisterDTO;
import com.newnocturnalhunter.api_rest.exceptions.BadRequestException;
import com.newnocturnalhunter.api_rest.exceptions.GenericException;
import com.newnocturnalhunter.api_rest.exceptions.MethodNotAllowedException;
import com.newnocturnalhunter.api_rest.exceptions.NotFoundException;
import com.newnocturnalhunter.api_rest.service.ClienteService;
import com.newnocturnalhunter.api_rest.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login") // -> http://localhost:8080/cliente/login
    public String login(@RequestBody ClienteLoginDTO clienteLoginDTO) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(clienteLoginDTO.getUsername(), clienteLoginDTO.getPassword())
            );
        } catch (NotFoundException e) {
            throw new NotFoundException("Credenciales del usuario incorrectas");
        }

        String token = ""; // Generamos el token
        try {
            token = tokenService.generateToken(authentication);
        } catch (GenericException e) {
            throw new GenericException("Error al generar el token de autenticación");

        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }

        // Retornamos el token
        return token;
    }

    @PostMapping("/register") // -> http://localhost:8080/cliente/register
    public ResponseEntity<?> register(@RequestBody ClienteRegisterDTO clienteRegisterDTO){
        try {
            clienteService.create(clienteRegisterDTO);
            return new ResponseEntity<>(clienteRegisterDTO, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (GenericException e) {
            throw new GenericException(e.getMessage());
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (MethodNotAllowedException e) {
            throw new MethodNotAllowedException(e.getMessage());
        }
    }
}
