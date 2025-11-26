package com.upc.ep.controller;

import com.upc.ep.entities.IndicadorFinanciero;
import com.upc.ep.services.IndicadorFinancieroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CrediHome")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true",
        exposedHeaders = "Authorization",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*"
)
public class IndicadorFinancieroController {
    @Autowired
    private IndicadorFinancieroService indicadorService;

    @PostMapping("/financiero")
    //@PreAuthorize("hasRole('ASESOR')")
    public IndicadorFinanciero save(@RequestBody IndicadorFinanciero entity) {
        return indicadorService.save(entity);
    }

    @GetMapping("/financiero/{id}")
    //@PreAuthorize("hasRole('ASESOR')")
    public Optional<IndicadorFinanciero> findById(@PathVariable Long id) {
        return indicadorService.findById(id);
    }

    @GetMapping("/financieros")
    //@PreAuthorize("hasRole('ASESOR')")
    public List<IndicadorFinanciero> findAll() {
        return indicadorService.findAll();
    }

    @PutMapping("/modificar/financiero/{id}")
    //@PreAuthorize("hasRole('ASESOR')")
    public IndicadorFinanciero update(@PathVariable Long id, @RequestBody IndicadorFinanciero entity) {
        return indicadorService.update(id, entity);
    }

    @DeleteMapping("/eliminar/financiero/{id}")
    //@PreAuthorize("hasRole('ASESOR')")
    public void delete(@PathVariable Long id) {
        indicadorService.deleteById(id);
    }
}
