package com.example.demo.controllers;

import com.example.demo.entities.ClienteEntity;
import com.example.demo.entities.DocumentacionEntity;
import com.example.demo.services.ClienteService;
import com.example.demo.services.DocumentacionService;
import jakarta.persistence.Lob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

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
            @RequestParam(value = "presupuestoRemodelacion", required = false) MultipartFile presupuestoRemodelacion,
            @RequestParam(value = "certificadoAntiguedadLaboral", required = false) MultipartFile certificadoAntiguedadLaboral,
            @RequestParam(value = "informeDeudas", required = false) MultipartFile informeDeudas,
            @RequestParam(value = "fotocopiaRut", required = false) MultipartFile fotocopiaRut,
            @RequestParam(value = "cuentaAhorros", required = false) MultipartFile cuentaAhorros
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
        if (certificadoAntiguedadLaboral != null)
            documento.setCertificadoAntiguedadLaboral(certificadoAntiguedadLaboral.getBytes());
        if (informeDeudas != null) documento.setInformeDeudas(informeDeudas.getBytes());
        if (fotocopiaRut != null) documento.setFotocopiaRut(fotocopiaRut.getBytes());
        if (cuentaAhorros != null) documento.setCuentaAhorros(cuentaAhorros.getBytes());

        DocumentacionEntity nuevoDocumento = documentacionService.guardaDocumento(documento);
        return ResponseEntity.ok(nuevoDocumento);
    }


    @GetMapping("/{rut}")
    public ResponseEntity<DocumentacionEntity> obtenerDocumentacionByRut(@PathVariable String rut) {
        Optional<DocumentacionEntity> documentacion = documentacionService.getByRut(rut);

        if (documentacion.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(documentacion.get());
        }
    }

}