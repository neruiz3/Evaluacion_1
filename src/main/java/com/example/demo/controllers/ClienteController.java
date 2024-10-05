package com.example.demo.controllers;

import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/v1/employees") hay que cambiar esto a lo mio
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    ClienteService clienteService;


}
