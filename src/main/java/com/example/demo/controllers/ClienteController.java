package com.example.demo.controllers;

import com.example.demo.entities.ClienteEntity;
import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/")
    public ResponseEntity<ClienteEntity> actualizaCliente(@RequestBody ClienteEntity cliente) {
        ClienteEntity clienteActualizado = clienteService.actualizaCliente(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @GetMapping("/")
    public ResponseEntity<List<ClienteEntity>> listaClientes() {
        List<ClienteEntity> clientes = clienteService.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Long id) {
        ClienteEntity cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<ClienteEntity> getClienteByRut(@PathVariable String rut) {
        ClienteEntity cliente = clienteService.getClienteByRut(rut);
        return ResponseEntity.ok(cliente);
    }




}
