package com.upc.ep.controller;

import com.upc.ep.entities.Credito;
import com.upc.ep.entities.IndicadorFinanciero;
import com.upc.ep.entities.PlanPago;
import com.upc.ep.services.CreditoService;
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
public class CreditoController {
    private CreditoService service;

    public CreditoController(CreditoService service) {
        this.service = service;
    }

    @PostMapping("/credito")
    @PreAuthorize("hasRole('ASESOR')")
    public Credito save(@RequestBody Credito c){
        return service.save(c);
    }

    @GetMapping("/credito/{id}")
    @PreAuthorize("hasRole('ASESOR')")
    public Credito find(@PathVariable Long id){
        return service.findById(id).orElse(null);
    }

    @GetMapping("/creditos")
    @PreAuthorize("hasRole('ASESOR')")
    public List<Credito> all(){
        return service.findAll();
    }

    @PutMapping("/modificar/{id}")
    @PreAuthorize("hasRole('ASESOR')")
    public Credito update(@PathVariable Long id, @RequestBody Credito c){
        return service.update(id, c);
    }

    @DeleteMapping("/credito/eliminar/{id}")
    @PreAuthorize("hasRole('ASESOR')")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }

    @PostMapping("/{id}/plan")
    @PreAuthorize("hasRole('ASESOR')")
    public List<PlanPago> generarPlan(
            @PathVariable Long id,
            @RequestParam int graciaTotal,
            @RequestParam int graciaParcial
    ){
        Credito credito = service.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado"));
        return service.generarPlanFrances(credito, graciaTotal, graciaParcial);
    }

    @PostMapping("/{id}/indicadores")
    @PreAuthorize("hasRole('ASESOR')")
    public IndicadorFinanciero indicadores(
            @PathVariable Long id,
            @RequestBody List<PlanPago> plan
    ){
        Credito credito = service.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado"));
        return service.calcularIndicadores(credito, plan);
    }
}

