package com.example.demo.services;

import org.springframework.stereotype.Service;

@Service
public class SimulacionService {
    public double calcularCuotaMensual(int monto, BigDecimal tasaInteres, int plazoAnios) {
        // Fórmula para el cálculo de la cuota mensual (hipoteca):
        // M = P[r(1+r)^n]/[(1+r)^n-1]
        BigDecimal cuotaMensual;
        int n = plazoAnios * 12; // Total de meses
        BigDecimal r = tasaInteres.divide(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);

        cuotaMensual = monto.multiply(r.multiply(BigDecimal.ONE.add(r).pow(n)))
                .divide(BigDecimal.ONE.add(r).pow(n).subtract(BigDecimal.ONE), RoundingMode.HALF_UP);
        return cuotaMensual;
    }
}
