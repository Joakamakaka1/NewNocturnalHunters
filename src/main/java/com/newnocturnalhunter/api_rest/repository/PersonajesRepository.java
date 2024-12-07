package com.newnocturnalhunter.api_rest.repository;

import com.newnocturnalhunter.api_rest.model.Personajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajesRepository extends JpaRepository<Personajes, Integer> {
}
