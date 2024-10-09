package com.example.demo.controllers;

import com.example.demo.TipoPrestamo;
import com.example.demo.entities.CreditoEntity;
import com.example.demo.entities.SimulacionEntity;
import com.example.demo.services.SimulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simulacion")
@CrossOrigin("*")
public class SimulacionController {

    @Autowired
    SimulacionService simulacionService;

    @GetMapping("/calculaSimulacion")
    public ResponseEntity<SimulacionEntity> calculaSimulacion(@RequestParam("monto") double monto, @RequestParam("plazo") int plazo,
                                                                     @RequestParam("tasaInteres") double tasaInteres,
                                                                     @RequestParam("tipoPrestamo") TipoPrestamo tipoPrestamo,
                                                                     @RequestParam("valorPropiedad") double valorPropiedad) {
        SimulacionEntity simulacion = simulacionService.calculaSimulacion(monto, plazo, tasaInteres, tipoPrestamo, valorPropiedad);
        return ResponseEntity.ok(simulacion);
    }
}
