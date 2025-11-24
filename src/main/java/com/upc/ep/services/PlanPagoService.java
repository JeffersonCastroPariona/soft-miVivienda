package com.upc.ep.services;

import com.upc.ep.entities.PlanPago;

import java.util.List;

public interface PlanPagoService {
    List<PlanPago> listarPlanesPago();

    PlanPago obtenerPlanPagoPorId(Long id);

    PlanPago guardarPlanPago(PlanPago planPago);

    void eliminarPlanPago(Long id);
}
