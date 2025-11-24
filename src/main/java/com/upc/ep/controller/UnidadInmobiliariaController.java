package com.upc.ep.controller;

import com.upc.ep.DTO.ClienteDTO;
import com.upc.ep.DTO.UnidadInmobiliariaDTO;
import com.upc.ep.entities.Cliente;
import com.upc.ep.entities.UnidadInmobiliaria;
import com.upc.ep.services.ClienteService;
import com.upc.ep.services.UnidadInmobiliarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/CrediHome")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true",
        exposedHeaders = "Authorization",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*"
)
public class UnidadInmobiliariaController {
    @Autowired
    private UnidadInmobiliarioService unidadInmobiliarioService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/unidad")
    @PreAuthorize("hasRole('ASESOR')")
    public UnidadInmobiliariaDTO saveUnidad(@RequestBody UnidadInmobiliariaDTO unidadInmobiliariaDTO) {
        UnidadInmobiliaria unidadInmobiliaria = modelMapper.map(unidadInmobiliariaDTO, UnidadInmobiliaria.class);
        unidadInmobiliaria = unidadInmobiliarioService.saveUnidad(unidadInmobiliaria);
        return modelMapper.map(unidadInmobiliaria, UnidadInmobiliariaDTO.class);
    }

    @GetMapping("/unidades")
    @PreAuthorize("hasRole('ASESOR')")
    public List<UnidadInmobiliariaDTO> listarU() {
        List<UnidadInmobiliaria> unidadInmobiliarias = unidadInmobiliarioService.listarU();
        ModelMapper modelMapper = new ModelMapper();
        return unidadInmobiliarias.stream()
                .map(unidadInmobiliaria -> modelMapper.map(unidadInmobiliaria, UnidadInmobiliariaDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping("/UNIDAD/modificar/{id}")
    @PreAuthorize("hasRole('ASESOR')")
    public ResponseEntity<UnidadInmobiliariaDTO> actualizarUnidad(@PathVariable Long id, @RequestBody UnidadInmobiliariaDTO unidadInmobiliariaDTO) {
        UnidadInmobiliariaDTO actualizada = unidadInmobiliarioService.actualizarUnidad(id, unidadInmobiliariaDTO);
        return new ResponseEntity<>(actualizada, HttpStatus.OK);
    }
}

