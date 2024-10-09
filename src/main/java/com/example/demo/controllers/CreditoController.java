package com.example.demo.controllers;

import com.example.demo.TipoPrestamo;
import com.example.demo.entities.CreditoEntity;
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
    public ResponseEntity<CreditoEntity> calculaSimulacion(@RequestParam("monto") double monto, @RequestParam("plazo") int plazo,
                                                              @RequestParam("tasaInteres") double tasaInteres,
                                                              @RequestParam("tipoPrestamo") TipoPrestamo tipoPrestamo) {
        CreditoEntity credito = creditoService.calculaSimulacion(monto, plazo, tasaInteres, tipoPrestamo);
        return ResponseEntity.ok(credito);
    }

    public ResponseEntity<CreditoEntity> revisionInicial(@RequestBody CreditoEntity credito) {
        CreditoEntity creditoRevisadoInicial = creditoService.revisionInicial(credito);
        return ResponseEntity.ok(creditoRevisadoInicial);
    }

    public ResponseEntity<CreditoEntity> evaluaCredito(@RequestBody CreditoEntity credito) {
        CreditoEntity creditoEvaluado = creditoService.evaluacionCredito(credito);
        return ResponseEntity.ok(creditoEvaluado);
    }
}
