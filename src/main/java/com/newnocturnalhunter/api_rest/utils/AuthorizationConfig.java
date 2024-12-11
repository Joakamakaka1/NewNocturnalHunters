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

/**
 * The type Authorization config.
 */
@Component
public class AuthorizationConfig {
    @Autowired
    private ClienteRepository usuarioRepository;
    @Autowired
    private StringToLong stringToLong;

    private AuthorizationManager<RequestAuthorizationContext> createIdAuthorizationManager(String pathPrefix) {
        return (authentication, object) -> {
            Authentication auth = authentication.get();

            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                return new AuthorizationDecision(true);
            }

            String path = object.getRequest().getRequestURI();
            String idString = path.replaceAll("/" + pathPrefix + "/", "");
            Long id = stringToLong.method(idString);

            if (id == null) {
                return new AuthorizationDecision(false);
            }

            return new AuthorizationDecision(true);
        };
    }

    /**
     * Gets usuario by username manager.
     *
     * @return the usuario by username manager
     */
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
                cliente = usuarioRepository.findByUsername(username).orElse(null);
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
        return createIdAuthorizationManager("partidas");
    }

    /**
     * Gets personajes id manager.
     *
     * @return the personajes id manager
     */
    public AuthorizationManager<RequestAuthorizationContext> getPersonajesIdManager() {
        return createIdAuthorizationManager("personajes");
    }

    /**
     * Gets enemigos id manager.
     *
     * @return the enemigos id manager
     */
    public AuthorizationManager<RequestAuthorizationContext> getEnemigosIdManager() {
        return createIdAuthorizationManager("enemigos");
    }
}
