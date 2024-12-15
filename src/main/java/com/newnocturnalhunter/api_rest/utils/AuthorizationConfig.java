package com.newnocturnalhunter.api_rest.utils;

import com.newnocturnalhunter.api_rest.exceptions.GenericException;
import com.newnocturnalhunter.api_rest.exceptions.NotFoundException;
import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.model.Partidas;
import com.newnocturnalhunter.api_rest.repository.ClienteRepository;
import com.newnocturnalhunter.api_rest.repository.EnemigosRepository;
import com.newnocturnalhunter.api_rest.repository.PartidasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

/**
 * The type Authorization config.
 */
@Component
public class AuthorizationConfig { // Clase que maneja las autorizaciones de la API REST
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PartidasRepository partidasRepository;
    @Autowired
    private StringToLong stringToLong;
    @Autowired
    private EnemigosRepository enemigosRepository;

    /**
     * Gets usuario by username manager.
     *
     * @return the usuario by username manager
     */

    // Metodos que crean un AuthorizationManager
    // que verifica si el usuario es admin o el id de la entidad coincide con el id del usuario autenticado
    public AuthorizationManager<RequestAuthorizationContext> getUsuarioByUsernameManager() {
        return (authentication, object) -> {
            Authentication auth = authentication.get();

            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                return new AuthorizationDecision(true);
            }

            String path = object.getRequest().getRequestURI();
            String username = path.replaceAll("/cliente/", "");

            Cliente cliente = null;
            try {
                cliente = clienteRepository.findByUsername(username).orElse(null);
            } catch (Exception e) {
                throw new GenericException("Error inesperado: " + e.getMessage());
            }

            if (cliente == null) {
                return new AuthorizationDecision(false);
            }

            if (cliente.getUsername().equals(auth.getName())) {
                return new AuthorizationDecision(true);
            }

            return new AuthorizationDecision(false);
        };
    }

    /**
     * Gets partidas id manager.
     *
     * @return the partidas id manager
     */
    public AuthorizationManager<RequestAuthorizationContext> getPartidasIdManager() {
        return (authentication, object) -> {
            Authentication auth = authentication.get();

            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                return new AuthorizationDecision(true);
            }

            String path = object.getRequest().getRequestURI();
            String idString = path.replaceAll("/partidas/", "");
            Long id = stringToLong.method(idString);

            if(id == null) {
                return new AuthorizationDecision(false);
            }

            Partidas partida = null;
            try {
                partida = partidasRepository.findById(id).orElse(null);
            } catch (Exception e) {
                throw new GenericException("Error inesperado: " + e.getMessage());
            }

            if (partida == null) {
                return new AuthorizationDecision(false);
            }

            String username = auth.getName();
            Cliente cliente = clienteRepository.findByUsername(username).orElse(null);

            if (cliente == null) {
                return new AuthorizationDecision(false);
            }

            if (!partida.getCliente().getId().equals(cliente.getId())) {
                return new AuthorizationDecision(false);
            }

            return new AuthorizationDecision(true);
        };
    }
}
