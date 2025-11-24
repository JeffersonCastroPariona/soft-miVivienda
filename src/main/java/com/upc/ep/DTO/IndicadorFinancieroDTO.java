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

public class IndicadorFinancieroDTO implements Serializable {
    private Long idIndicador;
    private Double VAN;
    private Double TIR;
    private LocalDate fechaCalculo;
    private Double TCEA;

    private CreditoDTO credito;
}
