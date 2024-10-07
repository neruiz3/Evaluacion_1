package com.example.demo.services;

import com.example.demo.TipoPrestamo;
import com.example.demo.entities.SimulacionEntity;
import com.example.demo.repositories.SimulacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulacionService {

    @Autowired
    SimulacionRepository simulacionRepository;
//Hay que poner condiciones en cuanto al monto y el resto de parametros? o es se pone en otro lado??
    public void calculaSimulacion (double monto, int plazo, double tasaInteres, TipoPrestamo tipoPrestamo){
        SimulacionEntity simulacion = new SimulacionEntity();

        simulacion.setMonto(monto);
        simulacion.setPlazo(plazo);
        simulacion.setTasaInteres(tasaInteres);
        simulacion.setTipoPrestamo(tipoPrestamo);

        if(validarSimulacion(simulacion)){
            simulacion.setCuotaMensual(calcularCuotaMensual(simulacion));
            simulacionRepository.save(simulacion); //ya se han establecido los valores de la simulacion, así que ahora lo guardamos en la BBDD
        }

    }

    private double calcularCuotaMensual(SimulacionEntity simulacion) {
        int n = simulacion.getPlazo() * 12; // numero total de pagos
        double r = simulacion.getTasaInteres() / 12 / 100; //tasa de interes mensual
        double p = simulacion.getMonto();
        // M = P[r(1+r)^n]/[(1+r)^n-1]
        return ((p*r*Math.pow((1+r),n))/(Math.pow((1+r),n)-1));
    }

    public boolean validarSimulacion(SimulacionEntity simulacion) {
        // Validar el plazo
        if (simulacion.getPlazo() > simulacion.getTipoPrestamo().getPlazoMaximo()) {
            throw new IllegalArgumentException("El plazo excede el máximo permitido para este tipo de préstamo.");
        }
        // Validar la tasa de interés
        if (simulacion.getTasaInteres() < simulacion.getTipoPrestamo().getTasaInteresMinima()
                || simulacion.getTasaInteres() > simulacion.getTipoPrestamo().getTasaInteresMaxima()) {
            throw new IllegalArgumentException("La tasa de interés está fuera del rango permitido.");
        }
        // Validar el monto
        double montoMax = simulacion.getTipoPrestamo().getMontoMaximo() / 100;
        if (simulacion.getMonto() > montoMax) {
            throw new IllegalArgumentException("El monto solicitado excede el máximo permitido para este tipo de préstamo.");
        }

        return true;
    }




}
