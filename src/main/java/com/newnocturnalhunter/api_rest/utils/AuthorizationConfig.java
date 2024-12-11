package com.newnocturnalhunter.api_rest.utils;

import com.newnocturnalhunter.api_rest.exceptions.GenericException;
import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationConfig {
    @Autowired
    private ClienteRepository usuarioRepository;
    @Autowired
    private StringToLong stringToLong;

    public AuthorizationManager<RequestAuthorizationContext> getUsuarioByIdManager() {
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
                cliente = usuarioRepository.findByUsername(username).orElse(null);
            } catch (Exception e) {
                throw new GenericException("error inesperado. " + e.getMessage());
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

    public AuthorizationManager<RequestAuthorizationContext> getPartidasIdManager() {
        return (authentication, object) -> {
            Authentication auth = authentication.get();

            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                return new AuthorizationDecision(true);
            }

            String path = object.getRequest().getRequestURI();
            String idPartida = path.replaceAll("/partidas/", "");
            Long id  = stringToLong.method(idPartida);

            if (id == null) {
                return new AuthorizationDecision(false);
            }

            return new AuthorizationDecision(true);
        };
    }

    public AuthorizationManager<RequestAuthorizationContext> getPersonajesIdManager() {
        return (authentication, object) -> {
            Authentication auth = authentication.get();

            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                return new AuthorizationDecision(true);
            }

            String path = object.getRequest().getRequestURI();
            String idPersonaje = path.replaceAll("/personajes/", "");
            Long id  = stringToLong.method(idPersonaje);

            if (id == null) {
                return new AuthorizationDecision(false);
            }

            return new AuthorizationDecision(true);
        };
    }

    public AuthorizationManager<RequestAuthorizationContext> getEnemigosIdManager() {
        return (authentication, object) -> {
            Authentication auth = authentication.get();

            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                return new AuthorizationDecision(true);
            }

            String path = object.getRequest().getRequestURI();
            String idEnemigo = path.replaceAll("/enemigos/", "");
            Long id  = stringToLong.method(idEnemigo);

            if (id == null) {
                return new AuthorizationDecision(false);
            }

            return new AuthorizationDecision(true);
        };
    }
}
