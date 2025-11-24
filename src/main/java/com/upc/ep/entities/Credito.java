package com.upc.ep.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Credito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredito;
    private String moneda;
    private String tipoTasa;
    private Double tasaInteres;
    private Long plazoMeses;
    private Double montoPrestamo;
    private Double capitalizacion;
    private LocalDate fechaInicio;
    private Double Bono;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "unidad_id")
    private UnidadInmobiliaria unidadInmobiliaria;
}
