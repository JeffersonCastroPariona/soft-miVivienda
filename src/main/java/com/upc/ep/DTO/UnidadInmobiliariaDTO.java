package com.upc.ep.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UnidadInmobiliariaDTO implements Serializable {
    private Long idUnidad;
    private String nombre;
    private String direccion;
    private Double precio;
    private String moneda;
    private Boolean estadoU;
    private LocalDate fechaRegistro;

    private AsesorDTO asesor;
}
