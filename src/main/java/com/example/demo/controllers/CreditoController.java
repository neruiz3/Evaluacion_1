package com.example.demo.controllers;

import com.example.demo.Estado;
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

    @PutMapping("/revisaInicial")
    public ResponseEntity<CreditoEntity> revisionInicial(@RequestBody CreditoEntity credito) {
        CreditoEntity creditoRevisadoInicial = creditoService.revisionInicial(credito);
        return ResponseEntity.ok(creditoRevisadoInicial);
    }
    @PutMapping("/evalua")
    public ResponseEntity<CreditoEntity> evaluaCredito(@RequestBody CreditoEntity credito) {
        CreditoEntity creditoEvaluado = creditoService.evaluacionCredito(credito);
        return ResponseEntity.ok(creditoEvaluado);
    }
    
    @PutMapping("/aprueba/{estado}")
    public ResponseEntity<CreditoEntity> cambioEstado(@RequestBody CreditoEntity credito, @PathVariable Estado estado) {
        CreditoEntity creditoAprobado = creditoService.cambioEstado(credito, estado);
        return ResponseEntity.ok(creditoAprobado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminaId(@PathVariable Long id) throws Exception {
        var isDeleted = creditoService.eliminaCredito(id);
        return ResponseEntity.noContent().build();
    }
}
