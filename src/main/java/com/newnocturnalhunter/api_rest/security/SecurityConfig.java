package com.newnocturnalhunter.api_rest.security;

import com.newnocturnalhunter.api_rest.exceptions.GenericException;
import com.newnocturnalhunter.api_rest.model.Cliente;
import com.newnocturnalhunter.api_rest.repository.ClienteRepository;
import com.newnocturnalhunter.api_rest.utils.AuthorizationConfig;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private RsaKeyProperties rsaKeys;
    @Autowired
    private ClienteRepository usuarioRepository;
    @Autowired
    private AuthorizationConfig authorizationConfig;

    /**
     * Security filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // TODO: Arreglar permisos de endpoints
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //Cliente Endpoints
                        .requestMatchers(HttpMethod.POST, "/cliente/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cliente/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cliente/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cliente/{username}").access(authorizationConfig.getUsuarioByUsernameManager())
                        .requestMatchers(HttpMethod.DELETE, "/cliente/{username}").access(authorizationConfig.getUsuarioByUsernameManager())
                        .requestMatchers(HttpMethod.PUT, "/cliente/{username}").access(authorizationConfig.getUsuarioByUsernameManager())
                        // Partidas Endpoints
                        .requestMatchers(HttpMethod.GET, "/partidas/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/partidas/{id}").access(authorizationConfig.getPartidasIdManager())
                        .requestMatchers(HttpMethod.DELETE, "/partidas/{id}").access(authorizationConfig.getPartidasIdManager())
                        .requestMatchers(HttpMethod.PUT, "/partidas/{id}").access(authorizationConfig.getPartidasIdManager())
                        .requestMatchers(HttpMethod.POST, "/partidas/").hasRole("ADMIN")
                        // Personajes Endpoints
                        .requestMatchers(HttpMethod.GET, "/personajes/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/personajes/{id}").access(authorizationConfig.getPersonajesIdManager())
                        .requestMatchers(HttpMethod.DELETE, "/personajes/{id}").access(authorizationConfig.getPersonajesIdManager())
                        .requestMatchers(HttpMethod.PUT, "/personajes/{id}").access(authorizationConfig.getPersonajesIdManager())
                        .requestMatchers(HttpMethod.POST, "/personajes/").hasRole("ADMIN")
                        // Enemigos Endpoints
                        .requestMatchers(HttpMethod.GET, "/enemigos/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/enemigos/{id}").access(authorizationConfig.getEnemigosIdManager())
                        .requestMatchers(HttpMethod.DELETE, "/enemigos/{id}").access(authorizationConfig.getEnemigosIdManager())
                        .requestMatchers(HttpMethod.PUT, "/enemigos/{id}").access(authorizationConfig.getEnemigosIdManager())
                        .requestMatchers(HttpMethod.POST, "/enemigos/").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication manager.
     *
     * @param authenticationConfiguration the authentication configuration
     * @return the authentication manager
     * @throws Exception the exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Jwt decoder jwt decoder.
     *
     * @return the jwt decoder
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    /**
     * Jwt encoder jwt encoder.
     *
     * @return the jwt encoder
     */
    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));

        return new NimbusJwtEncoder(jwks);
    }
}
