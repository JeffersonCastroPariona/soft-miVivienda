package com.upc.ep.services;

import com.upc.ep.DTO.ClienteDTO;
import com.upc.ep.DTO.CreditoDTO;
import com.upc.ep.entities.Cliente;
import com.upc.ep.entities.Credito;
import com.upc.ep.entities.IndicadorFinanciero;
import com.upc.ep.entities.PlanPago;

import java.util.List;
import java.util.Optional;

public interface CreditoService {
    Credito save(Credito entity);

    Optional<Credito> findById(Long id);

    List<Credito> findAll();

    Credito update(Long id, Credito entity);

    void deleteById(Long id);
    List<PlanPago> generarPlanFrances(Credito credito, int mesesGraciaTotales, int mesesGraciaParciales);

    IndicadorFinanciero calcularIndicadores(Credito credito, List<PlanPago> plan);
}
