package com.example.demo.controllers;

import com.example.demo.services.UsuarioService;
import com.example.demo.entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1/employees") hay que cambiar esto a lo mio
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;


}
