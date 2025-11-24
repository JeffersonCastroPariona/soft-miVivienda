package com.upc.ep.impl;

import com.upc.ep.entities.IndicadorFinanciero;
import com.upc.ep.repositories.IndicadorFinancieroRepos;
import com.upc.ep.services.IndicadorFinancieroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndicadorFinancieroIMPL implements IndicadorFinancieroService {
    @Autowired
    private IndicadorFinancieroRepos indicadorRepository;

    @Override
    public IndicadorFinanciero save(IndicadorFinanciero entity) {
        return indicadorRepository.save(entity);
    }

    @Override
    public Optional<IndicadorFinanciero> findById(Long id) {
        return indicadorRepository.findById(id);
    }

    @Override
    public List<IndicadorFinanciero> findAll() {
        return indicadorRepository.findAll();
    }

    @Override
    public IndicadorFinanciero update(Long id, IndicadorFinanciero entity) {
        IndicadorFinanciero existing = indicadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Indicador no encontrado"));

        existing.setVAN(entity.getVAN());
        existing.setTIR(entity.getTIR());
        existing.setTCEA(entity.getTCEA());
        existing.setFechaCalculo(entity.getFechaCalculo());
        existing.setCredito(entity.getCredito());

        return indicadorRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        indicadorRepository.deleteById(id);
    }
}
