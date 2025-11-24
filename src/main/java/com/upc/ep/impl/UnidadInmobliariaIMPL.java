package com.upc.ep.impl;

import com.upc.ep.DTO.ClienteDTO;
import com.upc.ep.DTO.UnidadInmobiliariaDTO;
import com.upc.ep.entities.Cliente;
import com.upc.ep.entities.UnidadInmobiliaria;
import com.upc.ep.repositories.ClienteRepos;
import com.upc.ep.repositories.UnidadInmobiliariaRepos;
import com.upc.ep.services.UnidadInmobiliarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadInmobliariaIMPL implements UnidadInmobiliarioService {
    @Autowired
    private UnidadInmobiliariaRepos unidadInmobiliariaRepos;

    @Override
    public UnidadInmobiliaria saveUnidad(UnidadInmobiliaria unidadInmobiliaria) {
        return unidadInmobiliariaRepos.save(unidadInmobiliaria);
    }

    @Override
    public List<UnidadInmobiliaria> listarU() {
        return unidadInmobiliariaRepos.findAll();
    }

    @Override
    public UnidadInmobiliariaDTO actualizarUnidad(Long id, UnidadInmobiliariaDTO unidadInmobiliariaDTO) {
        UnidadInmobiliaria unidadInmobiliaria = unidadInmobiliariaRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad no encontrado con ID: " + id));

        unidadInmobiliaria.setNombre(unidadInmobiliariaDTO.getNombre());
        unidadInmobiliaria.setDireccion(unidadInmobiliariaDTO.getDireccion());
        unidadInmobiliaria.setPrecio(unidadInmobiliariaDTO.getPrecio());
        unidadInmobiliaria.setMoneda(unidadInmobiliariaDTO.getMoneda());
        unidadInmobiliaria.setEstadoU(unidadInmobiliariaDTO.getEstadoU());
        unidadInmobiliaria.setFechaRegistro(unidadInmobiliariaDTO.getFechaRegistro());

        UnidadInmobiliaria actualizado = unidadInmobiliariaRepos.save(unidadInmobiliaria);

        UnidadInmobiliariaDTO dtoActualizado = new UnidadInmobiliariaDTO();
        dtoActualizado.setNombre(actualizado.getNombre());
        dtoActualizado.setDireccion(actualizado.getDireccion());
        dtoActualizado.setPrecio(actualizado.getPrecio());
        dtoActualizado.setMoneda(actualizado.getMoneda());
        dtoActualizado.setEstadoU(actualizado.getEstadoU());
        dtoActualizado.setFechaRegistro(actualizado.getFechaRegistro());


        return dtoActualizado;
    }

}

