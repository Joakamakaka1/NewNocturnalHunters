package com.newnocturnalhunter.api_rest.controller;

import ch.qos.logback.core.net.server.Client;
import com.newnocturnalhunter.api_rest.dto.ClienteDTO;
import com.newnocturnalhunter.api_rest.dto.ClienteLoginDTO;
import com.newnocturnalhunter.api_rest.dto.ClienteRegisterDTO;
import com.newnocturnalhunter.api_rest.dto.EnemigosDTO;
import com.newnocturnalhunter.api_rest.exceptions.*;
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
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/register");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/register");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/register");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/register");
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }catch (UnauthorizedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/register" );
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{userName}") // -> http://localhost:8080/cliente/{userName}
    public ResponseEntity<?> getCliente(@PathVariable String userName){
        try {
            ClienteDTO clienteDTO = clienteService.findByNombre(userName);
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        }catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + userName);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + userName);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + userName);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + userName);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }catch (UnauthorizedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + userName);
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/") // -> http://localhost:8080/cliente/
    public ResponseEntity<?> getAllClientes() {
        try{
            List<ClienteDTO> clientesDTO = clienteService.getAllClientes();
            return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
        }catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/");
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }catch (UnauthorizedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{userName}") // -> http://localhost:8080/cliente/{userName}
    public ResponseEntity<?> update(@PathVariable String username, @RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.update(username, clienteDTO);
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + username);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + username);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + username);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + username);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }catch (UnauthorizedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + username);
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{userName}") // -> http://localhost:8080/cliente/{userName}
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            clienteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BadRequestException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + id);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (GenericException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + id);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (MethodNotAllowedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + id);
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }catch (UnauthorizedException e) {
            ErrorMsg error = new ErrorMsg(e.getMessage(), "/cliente/" + id);
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }
}
