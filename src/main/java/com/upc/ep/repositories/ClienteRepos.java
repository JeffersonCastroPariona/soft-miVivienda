package com.upc.ep.repositories;

import com.upc.ep.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepos extends JpaRepository<Cliente, Long> {
}
