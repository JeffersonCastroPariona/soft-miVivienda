package com.upc.ep.impl;

import com.upc.ep.entities.Credito;
import com.upc.ep.entities.IndicadorFinanciero;
import com.upc.ep.entities.PlanPago;
import com.upc.ep.repositories.CreditoRepos;
import com.upc.ep.repositories.PlanPagoRepos;
import com.upc.ep.services.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditoIMPL implements CreditoService {
    @Autowired
    private CreditoRepos creditoRepository;
    @Autowired
    private PlanPagoRepos planRepo;


    @Override
    public Credito save(Credito entity) {
        return creditoRepository.save(entity);
    }

    @Override
    public java.util.Optional<Credito> findById(Long id) {
        return creditoRepository.findById(id);
    }

    @Override
    public List<Credito> findAll() {
        return creditoRepository.findAll();
    }

    @Override
    public Credito update(Long id, Credito entity) {
        Credito existing = creditoRepository.findById(id).orElseThrow(() -> new RuntimeException("Crédito no encontrado"));
        existing.setMoneda(entity.getMoneda());
        existing.setTipoTasa(entity.getTipoTasa());
        existing.setTasaInteres(entity.getTasaInteres());
        existing.setPlazoMeses(entity.getPlazoMeses());
        existing.setMontoPrestamo(entity.getMontoPrestamo());
        existing.setCapitalizacion(entity.getCapitalizacion());
        existing.setFechaInicio(entity.getFechaInicio());
        existing.setBono(entity.getBono());
        existing.setCliente(entity.getCliente());
        existing.setUnidadInmobiliaria(entity.getUnidadInmobiliaria());
        return creditoRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        creditoRepository.deleteById(id);
    }

    @Override
    public List<PlanPago> generarPlanFrances(Credito credito, int mesesGraciaTotales, int mesesGraciaParciales) {
        List<PlanPago> plan = new ArrayList<>();
        double monto = credito.getMontoPrestamo();
        int n = Math.toIntExact(credito.getPlazoMeses().longValue());
        LocalDate fecha = credito.getFechaInicio() != null ? credito.getFechaInicio() : LocalDate.now();

        double tasaAnualPct = credito.getTasaInteres() != null ? credito.getTasaInteres() : 0.0;
        double iMensual = convertirATasaMensual(tasaAnualPct, credito.getTipoTasa(), credito.getCapitalizacion());

        double saldo = monto;

        int mesesActivos = n - mesesGraciaTotales;
        if (mesesActivos <= 0) throw new IllegalArgumentException("Plazo inválido vs gracia");

        if (mesesGraciaTotales > 0) {
            double acumulado = 0.0;
            for (int m=0; m<mesesGraciaTotales; m++){
                double interes = saldo * iMensual;
                acumulado += interes;
            }
            saldo += acumulado;
        }
        double cuotaFija = (iMensual == 0.0) ? saldo / mesesActivos :
                (saldo * iMensual) / (1.0 - Math.pow(1.0 + iMensual, -mesesActivos));


        for (int m=0; m<mesesGraciaTotales; m++){
            PlanPago p = new PlanPago();
            p.setCredito(credito);
            p.setFechaPago(fecha.plusMonths(m));
            p.setSaldoInicial(saldo);
            p.setInteres(0.0);
            p.setAmortizacion(0.0);
            p.setCuota(0.0);
            p.setSaldoFinal(saldo);
            plan.add(p);
        }

        for (int m = 0; m < mesesGraciaParciales; m++){
            PlanPago p = new PlanPago();
            p.setCredito(credito);
            p.setFechaPago(fecha.plusMonths(mesesGraciaTotales + m));
            p.setSaldoInicial(saldo);
            double interes = saldo * iMensual;
            p.setInteres(interes);
            p.setAmortizacion(0.0);
            p.setCuota(interes);
            p.setSaldoFinal(saldo);
            plan.add(p);
        }

        int inicio = mesesGraciaTotales + mesesGraciaParciales;
        double saldoCorriente = saldo;
        for (int k = 0; k < mesesActivos - mesesGraciaParciales; k++){
            PlanPago p = new PlanPago();
            p.setCredito(credito);
            p.setFechaPago(fecha.plusMonths(inicio + k));
            p.setSaldoInicial(saldoCorriente);
            double interes = saldoCorriente * iMensual;
            double amort = cuotaFija - interes;
            if (amort < 0) amort = 0;
            double cuota = cuotaFija;
            double saldoFinal = saldoCorriente - amort;

            if (k == (mesesActivos - mesesGraciaParciales - 1)) {
                amort = saldoCorriente;
                cuota = amort + interes;
                saldoFinal = 0.0;
            }
            p.setInteres(interes);
            p.setAmortizacion(amort);
            p.setCuota(cuota);
            p.setSaldoFinal(saldoFinal);
            plan.add(p);
            saldoCorriente = saldoFinal;
        }

        return plan;
    }


    private double convertirATasaMensual(double tasaAnualPct, String tipoTasa, Double capitalizacion) {
        if (tasaAnualPct == 0) return 0.0;
        double r = tasaAnualPct / 100.0;
        if ("EFECTIVA".equalsIgnoreCase(tipoTasa)) {
            return Math.pow(1.0 + r, 1.0/12.0) - 1.0;
        } else {

            double m = (capitalizacion != null && capitalizacion > 0) ? capitalizacion : 12.0;
            double periodo = r / m;
            double efectivoAnual = Math.pow(1.0 + periodo, m) - 1.0;
            return Math.pow(1.0 + efectivoAnual, 1.0/12.0) - 1.0;
        }
    }

    @Override
    public IndicadorFinanciero calcularIndicadores(Credito credito, List<PlanPago> plan) {
        IndicadorFinanciero ind = new IndicadorFinanciero();
        // flujo mensual: 0 -> desembolso neto
        double desembolso = credito.getMontoPrestamo() - (credito.getBono() != null ? credito.getBono() : 0.0);
        List<Double> flujos = new ArrayList<>();
        flujos.add(-desembolso);
        for (PlanPago p : plan) {

            flujos.add(p.getCuota());
        }


        double iMensual = convertirATasaMensual(credito.getTasaInteres(), credito.getTipoTasa(), credito.getCapitalizacion());

        double van = 0.0;
        for (int t=0; t<flujos.size(); t++){
            van += flujos.get(t) / Math.pow(1.0 + iMensual, t);
        }
        double tirMensual = calcularIRR(flujos, 1e-7, 1000);
        double tirAnual = Math.pow(1 + tirMensual, 12) - 1.0;
        ind.setVAN(van);
        ind.setTIR(tirAnual * 100.0);
        ind.setFechaCalculo(LocalDate.now());
        ind.setTCEA(ind.getTIR());
        ind.setCredito(credito);
        return ind;
    }


    private double calcularIRR(List<Double> flujos, double tol, int maxIter) {

        double low = -0.999999;
        double high = 10.0;
        double fLow = npv(low, flujos);
        double fHigh = npv(high, flujos);
        if (Double.isNaN(fLow) || Double.isNaN(fHigh)) return 0.0;
        if (fLow * fHigh > 0) {

            double x = 0.01;
            for (int i=0;i<maxIter;i++){
                double f = npv(x,flujos);
                double d = dnpv(x,flujos);
                if (d == 0) break;
                double nx = x - f/d;
                if (Math.abs(nx - x) < tol) return nx;
                x = nx;
            }
            return 0.0;
        } else {
            for (int i=0;i<maxIter;i++){
                double mid = (low+high)/2.0;
                double fmid = npv(mid, flujos);
                if (Math.abs(fmid) < tol) return mid;
                if (fLow * fmid <= 0) {
                    high = mid;
                    fHigh = fmid;
                } else {
                    low = mid;
                    fLow = fmid;
                }
            }
            return (low+high)/2.0;
        }
    }

    private double npv(double tasa, List<Double> flujos) {
        double s = 0.0;
        for (int t=0;t<flujos.size();t++){
            s += flujos.get(t) / Math.pow(1.0+tasa, t);
        }
        return s;
    }
    private double dnpv(double tasa, List<Double> flujos) {
        double s = 0.0;
        for (int t=1;t<flujos.size();t++){
            s += -t * flujos.get(t) / Math.pow(1.0+tasa, t+1);
        }
        return s;
    }
}

