package com.newnocturnalhunter.api_rest.repository;

import com.newnocturnalhunter.api_rest.model.Partidas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Partidas repository.
 */
@Repository
public interface PartidasRepository extends JpaRepository<Partidas, Long> {
}
