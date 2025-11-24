package com.upc.ep.repositories;

import com.upc.ep.entities.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditoRepos extends JpaRepository<Credito, Long> {
}
