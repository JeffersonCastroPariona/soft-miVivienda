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

public class AsesorDTO implements Serializable {
    private Long idAsesor;
    private String nombreA;
    private String dniA;
    private String telefonoA;
    private Boolean estadoA;
    private String correoA;
    private LocalDate fechaIngreso;
}
