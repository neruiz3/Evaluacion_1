package com.example.demo.controllers;

import com.example.demo.TipoPrestamo;
import com.example.demo.services.SimulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simulaciones")
@CrossOrigin("*")
public class SimulacionController {

    @Autowired
    SimulacionService simulacionService;

    //hay que hacer una funcion para calcular la simulacion, que llame a simulacionService
    @GetMapping("/calculate")
    public ResponseEntity<Void> calculaSimulacion(@RequestParam("monto") double monto, @RequestParam("plazo") int plazo,
                                                  @RequestParam("tasaInteres") double tasaInteres,
                                                  @RequestParam("tipoPrestamo") TipoPrestamo tipoPrestamo,
                                                  @RequestParam("valorPropiedad") double valorPropiedad) {
        simulacionService.calculaSimulacion(monto, plazo, tasaInteres, tipoPrestamo, valorPropiedad);
        return ResponseEntity.noContent().build();
    }
}
