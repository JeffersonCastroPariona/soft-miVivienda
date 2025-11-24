package com.upc.ep.impl;

import com.upc.ep.entities.PlanPago;
import com.upc.ep.repositories.PlanPagoRepos;
import com.upc.ep.services.PlanPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanPagoIMPL implements PlanPagoService {
    @Autowired
    private PlanPagoRepos planPagoRepository;

    @Autowired
    public PlanPagoIMPL (PlanPagoRepos planPagoRepository) {
        this.planPagoRepository = planPagoRepository;
    }

    @Override
    public List<PlanPago> listarPlanesPago() {
        return planPagoRepository.findAll();
    }

    @Override
    public PlanPago obtenerPlanPagoPorId(Long id) {
        return planPagoRepository.findById(id)
                .orElse(null);
    }

    @Override
    public PlanPago guardarPlanPago(PlanPago planPago) {
        return planPagoRepository.save(planPago);
    }


    @Override
    public void eliminarPlanPago(Long id) {
        planPagoRepository.deleteById(id);
    }
}
