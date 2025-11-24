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
public class PlanPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlan;
    private Double cuota;
    private LocalDate fechaPago;
    private Double interes;
    private Double saldoInicial;
    private Double saldoFinal;
    private Double amortizacion;

    @OneToOne
    @JoinColumn(name = "credito_id")
    private Credito credito;
}

