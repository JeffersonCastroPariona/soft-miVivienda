package com.upc.ep.services;

import com.upc.ep.DTO.AsesorDTO;
import com.upc.ep.entities.Asesor;

import java.util.List;

public interface AsesorService {
    public Asesor saveAsesor(Asesor asesor);
    public List<Asesor> listarA();
    AsesorDTO actualizarAsesor(Long id, AsesorDTO asesorDTO);
}
