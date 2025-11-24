package com.upc.ep.services;

import com.upc.ep.DTO.UnidadInmobiliariaDTO;
import com.upc.ep.entities.UnidadInmobiliaria;

import java.util.List;

public interface UnidadInmobiliarioService {
    public UnidadInmobiliaria saveUnidad(UnidadInmobiliaria unidadInmobiliaria);
    public List<UnidadInmobiliaria> listarU();
    UnidadInmobiliariaDTO actualizarUnidad(Long id, UnidadInmobiliariaDTO unidadInmobiliariaDTO);
}
