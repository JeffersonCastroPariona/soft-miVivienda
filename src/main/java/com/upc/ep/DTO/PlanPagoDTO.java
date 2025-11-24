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

public class PlanPagoDTO implements Serializable {
    private Long idPlan;
    private Double cuota;
    private LocalDate fechaPago;
    private Double interes;
    private Double saldoInicial;
    private Double saldoFinal;
    private CreditoDTO credito;
    private Double amortizacion;
}
