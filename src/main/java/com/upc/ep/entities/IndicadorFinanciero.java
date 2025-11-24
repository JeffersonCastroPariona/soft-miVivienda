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
public class IndicadorFinanciero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIndicador;
    private Double VAN;
    private Double TIR;
    private LocalDate fechaCalculo;
    private Double TCEA;


    @OneToOne
    @JoinColumn(name = "credito_id")
    private Credito credito;
}
