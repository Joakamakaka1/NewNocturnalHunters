package com.newnocturnalhunter.api_rest.repository;

import com.newnocturnalhunter.api_rest.model.Enemigos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemigosRepository extends JpaRepository<Enemigos, Long> {
}
