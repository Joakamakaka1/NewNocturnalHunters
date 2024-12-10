package com.newnocturnalhunter.api_rest.repository;

import com.newnocturnalhunter.api_rest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUsername(String username);
    void deleteByUsername(String username);
}
