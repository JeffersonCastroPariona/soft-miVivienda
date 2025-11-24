package com.upc.ep.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Asesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsesor;
    private String nombreA;
    private String dniA;
    private String telefonoA;
    private Boolean estadoA;
    private String correoA;
    private LocalDate fechaIngreso;
}
