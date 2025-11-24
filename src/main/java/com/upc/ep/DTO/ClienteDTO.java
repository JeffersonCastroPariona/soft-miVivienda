package com.upc.ep.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ClienteDTO implements Serializable {
    private Long idCliente;
    private String nombre;
    private String dni;
    private String correo;
    private Double ingresoMensual;
    private Double gastoMensual;
    private String ocupacion;
}
