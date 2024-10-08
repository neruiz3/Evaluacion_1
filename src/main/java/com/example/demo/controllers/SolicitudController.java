package com.example.demo.controllers;

import com.example.demo.entities.ClienteEntity;
import com.example.demo.entities.SolicitudEntity;
import com.example.demo.services.ClienteService;
import com.example.demo.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/solicitudes")
@CrossOrigin("*")
public class SolicitudController {
    @Autowired
    SolicitudService solicitudService;

    @PostMapping("/")
    public ResponseEntity<ClienteEntity> nuevaSolicitud(@RequestBody SolicitudEntity solicitud) {
        SolicitudEntity nuevaSolicitud = solicitudService.guardaSolicitud(solicitud);
        return ResponseEntity.ok(nuevaSolicitud);
    }
}
