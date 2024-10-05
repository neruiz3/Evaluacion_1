package com.example.demo.services;

import com.example.demo.entities.SimulacionEntity;
import com.example.demo.repositories.SimulacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulacionService {

    @Autowired
    SimulacionRepository simulacionRepository;
//Hay que poner condiciones en cuanto al monto y el resto de parametros? o es se pone en otro lado??
    public void calculaSimulacion (int monto, int plazo, double tasaInteres, String tipoPrestamo){
        SimulacionEntity simulacion = new SimulacionEntity();
        simulacion.setMonto(monto);
        simulacion.setPlazo(plazo);
        simulacion.setTasaInteres(tasaInteres);
        simulacion.setTipoPrestamo(tipoPrestamo);
        simulacion.setCuotaMensual(calcularCuotaMensual(simulacion));

        //ya se han establecido los valores de la simulacion, así que ahora lo guardamos en la BBDD
        simulacionRepository.save(simulacion);
    }

    private double calcularCuotaMensual(SimulacionEntity simulacion) {
        int n = simulacion.getPlazo() * 12; // numero total de pagos
        double r = simulacion.getTasaInteres() / 12 / 100; //tasa de interes mensual
        int p = simulacion.getMonto();
        // M = P[r(1+r)^n]/[(1+r)^n-1]
        return ((p*r*Math.pow((1+r),n))/(Math.pow((1+r),n)-1));
    }




}
