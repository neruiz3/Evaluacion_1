package com.example.demo.controllers;

import com.example.demo.TipoPrestamo;
import com.example.demo.entities.CreditoEntity;
import com.example.demo.entities.SimulacionEntity;
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

    //funcion para solicitar el credito: primero hay que crear un expediente para cada credito
    @PostMapping("/")
    public ResponseEntity<CreditoEntity> nuevaSolicitud(@RequestBody CreditoEntity solicitud) {
        CreditoEntity nuevaSolicitud = creditoService.creaExpediente(solicitud);
        return ResponseEntity.ok(nuevaSolicitud);
    }

    @GetMapping("/calculaSimulacion")
    public ResponseEntity<Double> calculaSimulacion(@RequestParam("monto") double monto, @RequestParam("plazo") int plazo,
                                                              @RequestParam("tasaInteres") double tasaInteres,
                                                              @RequestParam("tipoPrestamo") TipoPrestamo tipoPrestamo,
                                                              @RequestParam("valorPropiedad") double valorPropiedad) {
        double cuotaMensual = creditoService.calculaSimulacion(monto, plazo, tasaInteres, tipoPrestamo, valorPropiedad);
        return ResponseEntity.ok(cuotaMensual);
    }
}
