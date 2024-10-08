package com.example.demo.controllers;

import com.example.demo.TipoPrestamo;
import com.example.demo.services.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credito")
@CrossOrigin("*")
public class CreditoController {

    @Autowired
    CreditoService creditoService;

    //funcion para simular el credito
    @GetMapping("/calculate")
    public ResponseEntity<Void> calculaSimulacion(@RequestParam("monto") double monto, @RequestParam("plazo") int plazo,
                                                  @RequestParam("tasaInteres") double tasaInteres,
                                                  @RequestParam("tipoPrestamo") TipoPrestamo tipoPrestamo,
                                                  @RequestParam("valorPropiedad") double valorPropiedad) {
        creditoService.calculaSimulacion(monto, plazo, tasaInteres, tipoPrestamo, valorPropiedad);
        return ResponseEntity.noContent().build();
    }

    //funcion para solicitar el credito
}
