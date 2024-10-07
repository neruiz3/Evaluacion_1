package com.example.demo.controllers;

import com.example.demo.entities.ClienteEntity;
import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cliente")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<ClienteEntity> nuevoCliente(@RequestBody ClienteEntity cliente) {
        ClienteEntity nuevoCliente = clienteService.guardaCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

}
