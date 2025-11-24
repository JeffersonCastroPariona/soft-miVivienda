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
public class UnidadInmobiliaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidad;
    private String nombre;
    private String direccion;
    private Double precio;
    private String moneda;
    private Boolean estadoU;
    private LocalDate fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "asesor_id")
    private Asesor asesor;
}
