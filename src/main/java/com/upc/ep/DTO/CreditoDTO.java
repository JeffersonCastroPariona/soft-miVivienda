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

public class CreditoDTO implements Serializable {
    private Long idCredito;
    private String moneda;
    private String tipoTasa;
    private Double tasaInteres;
    private Long plazoMeses;
    private Double montoPrestamo;
    private Double capitalizacion;
    private LocalDate fechaInicio;
    private Double Bono;

    private ClienteDTO cliente;
    private UnidadInmobiliariaDTO unidad;
}
