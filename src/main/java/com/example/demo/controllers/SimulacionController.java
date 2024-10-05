package com.example.demo.controllers;

import com.example.demo.services.SimulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/employees") hay que cambiar esto a lo mio
@CrossOrigin("*")
public class SimulacionController {

    @Autowired
    SimulacionService simulacionService;

    //hay que hacer una funcion para calcular la simulacion, que llame a simulacionService
    @GetMapping("/calculate")
    public ResponseEntity<Void> calculaSimulacion(@RequestParam("monto") int monto, @RequestParam("plazo") int plazo, @RequestParam("tasaInteres") double tasaInteres, @RequestParam("tipoPrestamo") String tipoPrestamo) {
        simulacionService.calculaSimulacion(monto, plazo, tasaInteres, tipoPrestamo);
        return ResponseEntity.noContent().build();
    }
}
