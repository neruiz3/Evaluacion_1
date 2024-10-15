package com.example.demo.controllers;

import com.example.demo.entities.ClienteEntity;
import com.example.demo.entities.DocumentacionEntity;
import com.example.demo.services.ClienteService;
import com.example.demo.services.DocumentacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/documentacion")
@CrossOrigin("*")
public class DocumentacionController {
    @Autowired
    DocumentacionService documentacionService;

    @PostMapping("/")
    public ResponseEntity<DocumentacionEntity> nuevoDocumento(@RequestBody DocumentacionEntity documento) {
        DocumentacionEntity nuevoDocumento = documentacionService.guardaDocumento(documento);
        return ResponseEntity.ok(nuevoDocumento);
    }


}

