package com.upc.ep.services;

import com.upc.ep.DTO.AsesorDTO;
import com.upc.ep.entities.Asesor;
import java.util.List;
import java.util.Optional; // Necesaria esta importación

public interface AsesorService {
    Asesor saveAsesor(Asesor asesor);
    List<Asesor> listarA();
    AsesorDTO actualizarAsesor(Long id, AsesorDTO asesorDTO);
    Optional<Asesor> buscarPorCorreo(String correo);
}