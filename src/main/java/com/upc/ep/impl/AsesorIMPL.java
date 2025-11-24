package com.upc.ep.impl;

import com.upc.ep.DTO.AsesorDTO;
import com.upc.ep.entities.Asesor;
import com.upc.ep.repositories.AsesorRepos;
import com.upc.ep.services.AsesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsesorIMPL implements AsesorService {
    @Autowired
    private AsesorRepos asesorRepos;

    @Override
    public Asesor saveAsesor(Asesor asesor) {
        return asesorRepos.save(asesor);
    }

    @Override
    public List<Asesor> listarA() {
        return asesorRepos.findAll();
    }

    @Override
    public AsesorDTO actualizarAsesor(Long id, AsesorDTO asesorDTO) {
        Asesor asesor = asesorRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("Asesor no encontrado con ID: " + id));

        asesor.setNombreA(asesorDTO.getNombreA());
        asesor.setDniA(asesorDTO.getDniA());
        asesor.setTelefonoA(asesorDTO.getTelefonoA());
        asesor.setEstadoA(asesorDTO.getEstadoA());
        asesor.setCorreoA(asesorDTO.getCorreoA());
        asesor.setFechaIngreso(asesorDTO.getFechaIngreso());

        Asesor actualizado = asesorRepos.save(asesor);

        AsesorDTO dtoActualizado = new AsesorDTO();
        dtoActualizado.setIdAsesor(actualizado.getIdAsesor());
        dtoActualizado.setNombreA(actualizado.getNombreA());
        dtoActualizado.setDniA(actualizado.getDniA());
        dtoActualizado.setTelefonoA(actualizado.getTelefonoA());
        dtoActualizado.setEstadoA(actualizado.getEstadoA());
        dtoActualizado.setCorreoA(actualizado.getCorreoA());
        dtoActualizado.setFechaIngreso(actualizado.getFechaIngreso());

        return dtoActualizado;
    }

}
