package com.upc.ep.controller;

import com.upc.ep.entities.PlanPago;
import com.upc.ep.services.PlanPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/CrediHome")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true",
        exposedHeaders = "Authorization",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*"
)
public class PlanPagoController {
    @Autowired
    private PlanPagoService planPagoService;

    @Autowired
    public PlanPagoController(PlanPagoService planPagoService) {
        this.planPagoService = planPagoService;
    }

    @GetMapping("/planes")
    //@PreAuthorize("hasRole('ASESOR')")
    public List<PlanPago> listar() {
        return planPagoService.listarPlanesPago();
    }

    @GetMapping("/plan/{id}")
    //@PreAuthorize("hasRole('ASESOR')")
    public PlanPago obtener(@PathVariable Long id) {
        return planPagoService.obtenerPlanPagoPorId(id);
    }

    @PostMapping("/plan")
    //@PreAuthorize("hasRole('ASESOR')")
    public PlanPago guardar(@RequestBody PlanPago planPago) {
        return planPagoService.guardarPlanPago(planPago);
    }


    @DeleteMapping("/eliminar/{id}")
    //@PreAuthorize("hasRole('ASESOR')")
    public void eliminar(@PathVariable Long id) {
        planPagoService.eliminarPlanPago(id);
    }
}
