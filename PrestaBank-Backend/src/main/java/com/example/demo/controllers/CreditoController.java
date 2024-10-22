package com.example.demo.controllers;

import com.example.demo.DTO.CostoDTO;
import com.example.demo.Estado;
import com.example.demo.DTO.TipoPrestamoDTO;
import com.example.demo.entities.CreditoEntity;
import com.example.demo.services.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/calculaSimulacion")
    public ResponseEntity<CreditoEntity> calculaSimulacion(@RequestBody CreditoEntity simulacion) {
        CreditoEntity credito = creditoService.calculaSimulacion(simulacion);
        return ResponseEntity.ok(credito);
    }

    @PutMapping("/revisaInicial")
    public ResponseEntity<CreditoEntity> revisionInicial(@RequestBody CreditoEntity credito) {
        CreditoEntity creditoRevisadoInicial = creditoService.revisionInicial(credito);
        return ResponseEntity.ok(creditoRevisadoInicial);
    }

    @PutMapping("/evaluar")
    public ResponseEntity<CreditoEntity> evaluaCredito(@RequestBody CreditoEntity credito) {
        CreditoEntity creditoEvaluado = creditoService.evaluacionCredito(credito);
        return ResponseEntity.ok(creditoEvaluado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditoEntity> cambioEstado(@PathVariable Long id,  @RequestBody Estado estado) {
        CreditoEntity creditoAprobado = creditoService.cambioEstado(id, estado);
        return ResponseEntity.ok(creditoAprobado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminaId(@PathVariable Long id) throws Exception {
        var isDeleted = creditoService.eliminaCredito(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CreditoEntity>> listaCreditos() {
        List<CreditoEntity> creditos = creditoService.getCreditos();
        if (creditos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(creditos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CreditoEntity> getCreditoId(@PathVariable Long id) {
        CreditoEntity credito = creditoService.getCredito(id);
        return ResponseEntity.ok(credito);
    }

    @GetMapping("/cliente/{rut}")
    public ResponseEntity<List<CreditoEntity>> listaCreditosCliente(@PathVariable String rut) {
        List<CreditoEntity> creditos = creditoService.getCreditosCliente(rut);
        if (creditos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(creditos);
    }

    @GetMapping("/tipo-prestamo")
    public List<TipoPrestamoDTO> obtenerTiposPrestamo() {
        List<TipoPrestamoDTO> prestamos = creditoService.obtenerTiposPrestamo();
        return prestamos;
    }

    @PostMapping ("/costoTotal")
    public CostoDTO costoTotal(@RequestBody CreditoEntity credito) {
       CostoDTO costos = creditoService.calculaCostoTotal(credito);
       return costos;
    }


}
