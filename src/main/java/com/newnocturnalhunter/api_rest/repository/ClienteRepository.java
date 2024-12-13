package com.newnocturnalhunter.api_rest.repository;

import com.newnocturnalhunter.api_rest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Cliente repository.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Cliente> findByUsername(String username); // -> SELECT * FROM clientes WHERE username = ? LIMIT 1
}
