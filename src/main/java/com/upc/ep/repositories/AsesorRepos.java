package com.upc.ep.repositories;

import com.upc.ep.entities.Asesor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Necesaria esta importación

public interface AsesorRepos extends JpaRepository<Asesor, Long> {
  Optional<Asesor> findByCorreoA(String correo);
}