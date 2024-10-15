package com.example.demo.controllers;

import com.example.demo.entities.ClienteEntity;
import com.example.demo.entities.DocumentacionEntity;
import com.example.demo.services.ClienteService;
import com.example.demo.services.DocumentacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/documentacion")
@CrossOrigin("*")
public class DocumentacionController {
    @Autowired
    DocumentacionService documentacionService;

    /*@PostMapping("/")
    public ResponseEntity<DocumentacionEntity> nuevoDocumento(@RequestBody DocumentacionEntity documento) {
        DocumentacionEntity nuevoDocumento = documentacionService.guardaDocumento(documento);
        return ResponseEntity.ok(nuevoDocumento);
    }
*/
    @PostMapping("/")
    public ResponseEntity<DocumentacionEntity> nuevoDocumento(
            @RequestParam("rut") String rut,
            @RequestParam(value = "comprobanteIngresos", required = false) MultipartFile comprobanteIngresos,
            @RequestParam(value = "escrituraVivienda", required = false) MultipartFile escrituraVivienda,
            @RequestParam(value = "historialCrediticio", required = false) MultipartFile historialCrediticio,
            @RequestParam(value = "certificadoAvaluo", required = false) MultipartFile certificadoAvaluo,
            @RequestParam(value = "estadoNegocio", required = false) MultipartFile estadoNegocio,
            @RequestParam(value = "planNegocio", required = false) MultipartFile planNegocio,
            @RequestParam(value = "presupuestoRemodelacion", required = false) MultipartFile presupuestoRemodelacion
    ) throws IOException {
        DocumentacionEntity documento = new DocumentacionEntity();
        documento.setRut(rut);
        if (comprobanteIngresos != null) documento.setComprobanteIngresos(comprobanteIngresos.getBytes());
        if (escrituraVivienda != null) documento.setEscrituraVivienda(escrituraVivienda.getBytes());
        if (historialCrediticio != null) documento.setHistorialCrediticio(historialCrediticio.getBytes());
        if (certificadoAvaluo != null) documento.setCertificadoAvaluo(certificadoAvaluo.getBytes());
        if (estadoNegocio != null) documento.setEstadoNegocio(estadoNegocio.getBytes());
        if (planNegocio != null) documento.setPlanNegocio(planNegocio.getBytes());
        if (presupuestoRemodelacion != null) documento.setPresupuestoRemodelacion(presupuestoRemodelacion.getBytes());

        DocumentacionEntity nuevoDocumento = documentacionService.guardaDocumento(documento);
        return ResponseEntity.ok(nuevoDocumento);
    }
}

