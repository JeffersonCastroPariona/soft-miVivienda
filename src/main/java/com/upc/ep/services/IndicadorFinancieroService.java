package com.upc.ep.services;

import com.upc.ep.entities.IndicadorFinanciero;

import java.util.List;
import java.util.Optional;

public interface IndicadorFinancieroService {
    IndicadorFinanciero save(IndicadorFinanciero entity);

    Optional<IndicadorFinanciero> findById(Long id);

    List<IndicadorFinanciero> findAll();

    IndicadorFinanciero update(Long id, IndicadorFinanciero entity);

    void deleteById(Long id);
}

