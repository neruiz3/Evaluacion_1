package com.example.demo.controllers;

import com.example.demo.entities.ClienteEntity;
import com.example.demo.entities.SolicitudEntity;
import com.example.demo.services.SimulacionService;
import com.example.demo.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/employees") hay que cambiar esto a lo mio
@CrossOrigin("*")
public class SolicitudController {
    @Autowired
    SolicitudService solicitudService;

    @PostMapping("/")
    public ResponseEntity<SolicitudEntity> nuevaSolicitud(@RequestBody SolicitudEntity solicitud) {
        SolicitudEntity nuevaSolicitud = solicitudService.guardaSolicitud(solicitud);
        return ResponseEntity.ok(nuevaSolicitud);
    }
}
